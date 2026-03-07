package com.example.jetpackcomposesnippet.workmanager


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.work.BackoffPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.jetpackcomposesnippet.ui.theme.JetPackComposeSnippetTheme
import com.example.jetpackcomposesnippet.workmanager.presentation.ui.QuoteScreen
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val workManager = WorkManager.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val downloadRequest = OneTimeWorkRequestBuilder<DownloadWorker>().setConstraints(
            androidx.work.Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
        ).build()

        val colorFilterRequest = OneTimeWorkRequestBuilder<ColorFilterWorker>().build()
        val workManager = WorkManager.getInstance(applicationContext)

        enableEdgeToEdge()
        setContent {
            JetPackComposeSnippetTheme {
                QuoteScreen()
                //doWork()
                /*val workInfos = workManager.getWorkInfosForUniqueWorkLiveData("download")
                    .observeAsState()
                    .value
                val downloadInfo = remember(key1 = workInfos) {
                    workInfos?.find { it.id == downloadRequest.id }
                }
                val filterInfo = remember(key1 = workInfos) {
                    workInfos?.find { it.id == colorFilterRequest.id }
                }
                val imageUri by derivedStateOf {
                    val downloadUri =
                        downloadInfo?.outputData?.getString(WorkerKeys.IMAGE_URI)?.toUri()
                    val filterUri =
                        filterInfo?.outputData?.getString(WorkerKeys.FILTER_URI)?.toUri()
                    filterUri ?: downloadUri
                }
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    imageUri?.let { uri ->
                        AsyncImage(
                            model = rememberImagePainter(data = uri),
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    Button(
                        onClick = {
                            workManager.beginUniqueWork(
                                "download",
                                ExistingWorkPolicy.KEEP,
                                downloadRequest
                            ).then(colorFilterRequest).enqueue()
                        },
                        enabled = downloadInfo?.state != WorkInfo.State.RUNNING
                    ) {
                        Text("Start download")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    when (downloadInfo?.state) {
                        WorkInfo.State.RUNNING -> Text("Downloading...")
                        WorkInfo.State.SUCCEEDED -> Text("Download succeeded")
                        WorkInfo.State.FAILED -> Text("Download failed")
                        WorkInfo.State.CANCELLED -> Text("Download cancelled")
                        WorkInfo.State.ENQUEUED -> Text("Download enqueued")
                        WorkInfo.State.BLOCKED -> Text("Download blocked")
                        else -> {}
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    when (filterInfo?.state) {
                        WorkInfo.State.RUNNING -> Text("Applying Filter...")
                        WorkInfo.State.SUCCEEDED -> Text("Filter succeeded")
                        WorkInfo.State.FAILED -> Text("Filter failed")
                        WorkInfo.State.CANCELLED -> Text("Filter cancelled")
                        WorkInfo.State.ENQUEUED -> Text("Filter enqueued")
                        WorkInfo.State.BLOCKED -> Text("Filter blocked")
                        else -> {}
                    }
                }*/
            }
        }
    }

    fun doWork() {
        val request = OneTimeWorkRequest.Builder(DemoWorker::class.java).setConstraints(
            androidx.work.Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED).build()
        ).setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.SECONDS)
            .build()
        workManager.enqueue(request)
        workManager.beginWith(request).then(request).enqueue()
        workManager.getWorkInfoByIdLiveData(request.id).observe(this) {
            if (it != null) {
                printStatus(it.state.name)
            }
        }
    }

    fun printStatus(name: String) {
        Log.d("ANSHUPAUL", name)
    }
}