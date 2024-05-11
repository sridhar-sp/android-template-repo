package com.droidstarter.coroutine

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidstarter.Instrumentation
import com.droidstarter.InstrumentationImpl
import com.droidstarter.logD
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger

class HomeViewModel : ViewModel() {

    private fun Long.fromBytesToMB() = this / (1024 * 1024)

    companion object {
        const val TAG = "HomeViewModel"
        private const val TASK_COUNT = 50000
        private const val TASK_DELAY = 100L
    }

    var counter by mutableIntStateOf(0)
        private set

    var runNThreadState by mutableStateOf("Run $TASK_COUNT Thread")
        private set
    var runNThreadTimeConsumedState by mutableStateOf("NA")
        private set

    var runNCoroutineState by mutableStateOf("Run $TASK_COUNT Coroutine")
        private set
    var runNCoroutineTimeConsumedState by mutableStateOf("NA")
        private set

    private var runNCoroutineCount = AtomicInteger(0)
    var runNCoroutineProgress by mutableStateOf("NA")
        private set

    private val executors = Executors.newCachedThreadPool()

    var threadCount by mutableIntStateOf(-1)
        private set

    private val instrumentation = InstrumentationImpl.newInstance(logger = object : Instrumentation.Logger {
        override fun log(content: String) {
            Log.d(TAG, content)
        }
    })

    init {
        readThreadCount()
    }

    fun readThreadCount() {
        threadCount = Thread.activeCount()
        runNCoroutineProgress = ((runNCoroutineCount.get())).toString()
    }

    fun runNThread() {
        val tag = "runNThread"
        Log.d(TAG, "${Thread.currentThread().name} Start")
        runNThreadState = "Running"
        instrumentation.start(tag)

        executors.submit {
            for (i in 0..TASK_COUNT) {
                executors.submit {
                    Thread.sleep(TASK_DELAY)
                    Log.d(TAG, "${Thread.currentThread().name} $i |")
                }
            }
            runNThreadState = "Run $TASK_COUNT Thread"

            val stop = instrumentation.stop(tag)
            runNThreadTimeConsumedState = "$stop ms"
        }

        Log.d(TAG, "${Thread.currentThread().name} End")
    }

    fun runNCoroutine() {
        val tag = "runNCoroutine"
        logD("$tag Start Block")
        runNCoroutineState = "Running"
        runNCoroutineCount.set(0)

        instrumentation.start(tag)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                for (i in 0..TASK_COUNT) {
                    launch {
                        Thread.sleep(TASK_DELAY) // vs delay()
                        runNCoroutineCount.set(runNCoroutineCount.incrementAndGet())
                    }
                }
                runNCoroutineState = "Run $TASK_COUNT Coroutine"

                val stop = instrumentation.stop(tag)
                runNCoroutineTimeConsumedState = "$stop ms"
            }
        }

        logD("$tag End Block")
    }

    fun incrementCounter() {
        counter += 1
    }

}