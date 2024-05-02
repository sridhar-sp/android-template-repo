package com.droidstarter.coroutine

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors

class HomeViewModel : ViewModel() {

    private fun Long.fromBytesToMB() = this / (1024 * 1024)

    companion object {
        const val TAG = "HomeViewModel"
        const val TASK_COUNT = 50000
        const val TASK_DELAY = 100L
    }

    val message = "Sridhar"

    var counter by mutableStateOf(0)

    var runNThreadState by mutableStateOf("Run N Thread")

    var runNCoroutineState by mutableStateOf("Run N Coroutine")

    val executors = Executors.newCachedThreadPool()

    var threadCount by mutableIntStateOf(-1)

    init {
        readThreadCount()
    }

    fun readThreadCount() {
        threadCount = Thread.activeCount()
    }

    fun runNThread() {
        Log.d(TAG, "${Thread.currentThread().name} Start")
        runNThreadState = "Running"
        executors.submit {
            for (i in 0..TASK_COUNT) {
                executors.submit {
                    Thread.sleep(TASK_DELAY)
                    Log.d(TAG, "${Thread.currentThread().name} $i |")
                }
            }
            runNThreadState = "Run N Thread"

        }
        Log.d(TAG, "${Thread.currentThread().name} End")
    }

    fun runNCoroutine() {
        Log.d(TAG, "${Thread.currentThread().name} Start")
        runNCoroutineState = "Running"

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                for (i in 0..TASK_COUNT) {
                    launch {
                        Thread.sleep(TASK_DELAY)
                        Log.d(TAG, "${Thread.currentThread().name} $i |")
                    }
                }
                runNCoroutineState = "Run N Coroutine"
            }
        }

        Log.d(TAG, "${Thread.currentThread().name} End")
    }

     fun incrementCounter() {
        counter += 1
    }

    private suspend fun a() {
        withContext(Dispatchers.IO) {

        }
    }
}