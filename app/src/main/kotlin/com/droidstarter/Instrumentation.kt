package com.droidstarter

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit

interface Instrumentation {
    var logger: Logger?
    var timeUnit: TimeUnit

    @Throws(IllegalArgumentException::class)
    fun start(sessionKey: String)

    @Throws(InvalidSession::class, IllegalArgumentException::class)
    fun stop(sessionKey: String): Long

    @Throws(InvalidSession::class, IllegalArgumentException::class)
    fun getDuration(sessionKey: String): Long

    fun clear()

    data class Session(private val mSessionKey: String) {
        private val mStartTime: Long = System.currentTimeMillis()
        var mStopTime: Long = -1L

        val duration: Long
            get() = mStopTime - mStartTime

        fun getSessionKey() = mSessionKey

        fun getStartTime() = mStartTime

        fun getStopTime() = mStopTime
    }

    class InvalidSession(sessionKey: String) :
        Exception("Session with $sessionKey does not exist")

    interface Logger {
        fun log(content: String)
    }
}

class InstrumentationImpl : Instrumentation {

    companion object {
//        private lateinit var INSTANCE: Instrumentation
//
//        fun init(logger: Instrumentation.Logger? = null, timeUnit: TimeUnit = TimeUnit.SECONDS) :Instrumentation{
//            INSTANCE = InstrumentationImpl()
//            INSTANCE.logger = logger
//            INSTANCE.timeUnit = timeUnit
//            return INSTANCE
//        }
//
//        fun getInstance(): Instrumentation {
//            if (!this::INSTANCE.isInitialized) {
//                throw RuntimeException("Instrumentation not initialised. Call init first")
//            }
//            return INSTANCE
//        }

        fun newInstance(logger: Instrumentation.Logger? = null, timeUnit: TimeUnit = TimeUnit.SECONDS) :Instrumentation{
            val instrumentation = InstrumentationImpl()
            instrumentation.logger = logger
            instrumentation.timeUnit = timeUnit
            return instrumentation
        }
    }

    private val sessions = ConcurrentHashMap<String, Instrumentation.Session>()
    private val dateFormat = SimpleDateFormat("hh:mm:ss.SSS", Locale.ROOT)

    override var logger: Instrumentation.Logger? = null
    override var timeUnit: TimeUnit = TimeUnit.SECONDS

    override fun start(sessionKey: String) {
        throwIfSessionKeyNotValid(sessionKey)
        val session = Instrumentation.Session(sessionKey)
        sessions[sessionKey] = session
        log(constructLogStatement(session))
    }

    override fun stop(sessionKey: String): Long {
        throwIfSessionKeyNotValid(sessionKey)
        throwIfSessionKeyNotPresent(sessionKey)

        val session = sessions[sessionKey]
        session!!.mStopTime = System.currentTimeMillis()
        log(constructLogStatement(session))
        return session.duration
    }

    override fun getDuration(sessionKey: String): Long {
        throwIfSessionKeyNotValid(sessionKey)
        throwIfSessionKeyNotPresent(sessionKey)

        return sessions[sessionKey]!!.duration
    }

    override fun clear() {
        val sessionsCount = sessions.size
        sessions.clear()
        log("Cleared $sessionsCount session/s from instrumentation")
    }

    @Throws(java.lang.IllegalArgumentException::class)
    private fun throwIfSessionKeyNotValid(sessionKey: String) {
        if (sessionKey.isEmpty())
            throw java.lang.IllegalArgumentException("Session key should not be empty")
    }

    @Throws(Instrumentation.InvalidSession::class)
    private fun throwIfSessionKeyNotPresent(sessionKey: String) {
        if (!sessions.containsKey(sessionKey))
            throw Instrumentation.InvalidSession(sessionKey)
    }

    private fun constructLogStatement(session: Instrumentation.Session): String =
        if (session.mStopTime == -1L) {
            "Starting instrumentation for ${session.getSessionKey()} at ${formatTimeInMillis(session.getStartTime())}"
        } else {
            "Stopped instrumentation for ${session.getSessionKey()} with duration ${
                constructDuration(
                    session.duration
                )
            }"
        }

    private fun log(content: String) {
        logger?.log(content)
    }

    private fun constructDuration(duration: Long): String {
        val durationInDesiredUnit = timeUnit.convert(duration, TimeUnit.MILLISECONDS)
        return if (durationInDesiredUnit == 0L) {
            "$duration ${TimeUnit.MILLISECONDS.name}"
        } else {
            "$durationInDesiredUnit ${timeUnit.name}"
        }
    }

    private fun formatTimeInMillis(timeInMillis: Long): String {
        return dateFormat.format(timeInMillis)
    }
}
