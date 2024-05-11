package com.droidstarter.coroutine

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.droidstarter.Instrumentation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors

class RunNTask(
    val taskCount: Int,
    val taskDelay: Long,
    val mainScope: CoroutineScope,
    val instrumentation: Instrumentation
) {

    var nThreadTaskState by mutableStateOf<TaskState>(TaskState.New)

    var nCoroutineTaskState by mutableStateOf<TaskState>(TaskState.New)

    private val executors = Executors.newCachedThreadPool()


    fun runNThread() {
        val tag = "runNThread"
        nThreadTaskState = TaskState.Running
        instrumentation.start(tag)

        executors.submit {
            for (i in 0..taskCount) {
                executors.submit {
                    Thread.sleep(taskDelay)
                    // Log.d(HomeViewModel.TAG, "${Thread.currentThread().name} $i |")
                }
            }
            nThreadTaskState = TaskState.Done(timeConsumedInMillis = instrumentation.stop(tag))
        }
    }

    fun runNCoroutine() {
        val tag = "runNCoroutine"
        nCoroutineTaskState = TaskState.Running
        instrumentation.start(tag)

        mainScope.launch {
            withContext(Dispatchers.IO) {
                for (i in 0..taskCount) {
                    launch {
                        Thread.sleep(taskDelay) // vs delay()
//                        Log.d(HomeViewModel.TAG, "${Thread.currentThread().name} $i |")
                    }
                }
                nCoroutineTaskState = TaskState.Done(timeConsumedInMillis = instrumentation.stop(tag))
            }
        }

    }

}

sealed class TaskState {
    data object New : TaskState()

    data object Running : TaskState()

    data class Done(val timeConsumedInMillis: Long) : TaskState()
}