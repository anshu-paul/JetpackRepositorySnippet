package com.example.jetpackcomposesnippet.workmanager.worker

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.jetpackcomposesnippet.R
import com.example.jetpackcomposesnippet.workmanager.data.model.QuoteDTO
import com.google.gson.Gson
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class NotificationWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                val quoteJson = params.inputData.getString("quote")
                val quote = Gson().fromJson(quoteJson, QuoteDTO::class.java)
                val notification = NotificationCompat.Builder(context, "channel")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("Quote's")
                    .setContentText(quote.quote.plus(" ${quote.author}"))
                    .build()
                NotificationManagerCompat.from(context).notify(1, notification)
            }
        }
        return Result.success()
    }
}