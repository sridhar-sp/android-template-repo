package com.droidstarter

import android.util.Log

private val LOG_LEVEL = Log.VERBOSE

private const val LOG_TAG_PREFIX = "cp::"

internal fun Any.logTag(tag: String? = null): String {

    if (tag != null) return "${LOG_TAG_PREFIX}$tag"

    if (!javaClass.isAnonymousClass)
        return "${LOG_TAG_PREFIX}${javaClass.simpleName}"

    return "${LOG_TAG_PREFIX}${javaClass.name}"
}

fun Any.logE(message: String, tag: String? = null) {
    if (LOG_LEVEL <= Log.ERROR) Log.e(logTag(tag), message)
}

fun Any.logE(message: String, tag: String? = null, throwable: Throwable?) {
    if (LOG_LEVEL <= Log.ERROR) Log.e(logTag(tag), message, throwable)
}

fun Any.logW(message: String, tag: String? = null) {
    if (LOG_LEVEL <= Log.WARN) Log.w(logTag(tag), message)
}

fun Any.logI(message: String, tag: String? = null) {
    if (LOG_LEVEL <= Log.INFO) Log.i(logTag(tag), message)
}

fun Any.logD(message: String, tag: String? = null) {
    if (LOG_LEVEL <= Log.DEBUG)
        Log.d(logTag(tag), message)
}

fun Any.logV(message: String, tag: String? = null) {
    if (LOG_LEVEL <= Log.VERBOSE)
        Log.v(logTag(tag), message)
}

object Logger {
}


