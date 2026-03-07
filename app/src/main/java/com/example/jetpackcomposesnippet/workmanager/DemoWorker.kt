package com.example.jetpackcomposesnippet.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class DemoWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        performWork()
        return Result.retry()
    }

    fun performWork() {
        Thread.sleep(2000)
        Log.d("ANSHUPAUL", "Task Completed")
    }
}