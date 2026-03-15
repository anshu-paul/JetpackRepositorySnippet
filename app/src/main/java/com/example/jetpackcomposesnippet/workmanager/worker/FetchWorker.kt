package com.example.jetpackcomposesnippet.workmanager.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import com.example.jetpackcomposesnippet.workmanager.data.remote.ApiService
import com.example.jetpackcomposesnippet.workmanager.local.QuoteDao
import com.google.gson.Gson
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject


const val ONE_TIME_WORK_REQUEST = "ONE_TIME_WORK_REQUEST"
const val PERIODIC_WORK_REQUEST = "periodic_work_request"


@HiltWorker
class FetchWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val params: WorkerParameters,
    private val apiService: ApiService,
    private val quoteDao: QuoteDao
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        return try {
            val response = apiService.getQuotes()?.body()
            response?.let {
                quoteDao.insertQuote(it)
                val data = Data.Builder().putString("quote", Gson().toJson(response)).build()
                Result.success(data)
            } ?: Result.failure()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}