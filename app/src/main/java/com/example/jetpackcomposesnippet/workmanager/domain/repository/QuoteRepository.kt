package com.example.jetpackcomposesnippet.workmanager.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.jetpackcomposesnippet.workmanager.data.model.QuoteDTO
import com.example.jetpackcomposesnippet.workmanager.local.QuoteDao
import com.example.jetpackcomposesnippet.workmanager.worker.FetchWorker
import com.example.jetpackcomposesnippet.workmanager.worker.NotificationWorker
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val workManager: WorkManager,
    private val quoteDao: QuoteDao
) {

    private val _quotes = MutableLiveData<QuoteDTO>()
    val quotes: LiveData<QuoteDTO> = _quotes

    fun getQuotes() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest =
            OneTimeWorkRequestBuilder<FetchWorker>().setConstraints(constraints).build()
        val notificationWorkRequest =
            OneTimeWorkRequestBuilder<NotificationWorker>().build()
        workManager.beginWith(workRequest).then(notificationWorkRequest).enqueue()

        //workManager.enqueue(workRequest)

        /*val quotesResult = apiService.getQuotes()
        if (quotesResult?.isSuccessful!! && quotesResult.body() != null) {
            _quotes.postValue(quotesResult.body())
        }*/
    }

    fun getAllQuotes(): Flow<List<QuoteDTO>> = quoteDao.getAllQuotes()

}