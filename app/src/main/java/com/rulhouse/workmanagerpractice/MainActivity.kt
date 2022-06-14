package com.rulhouse.workmanagerpractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.work.*
import com.rulhouse.evgawatcher.crawler.use_cases.CrawlerUseCases
import com.rulhouse.workmanagerpractice.work_manager.use_cases.WorkManagerUseCases
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var workManagerUseCases: WorkManagerUseCases

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        workManagerUseCases.setPeriodicWork()

        setContent {
//            val constraints = Constraints.Builder()
//                .setRequiredNetworkType(NetworkType.CONNECTED)
//                .build()
//
//            val request = PeriodicWorkRequest.Builder(MyWorker::class.java, 15, TimeUnit.MINUTES)
//                .setConstraints(constraints)
//                .build()
//
////            WorkManager.getInstance(this).enqueue(request)
//            WorkManager.getInstance(this).enqueueUniquePeriodicWork(
//                "sendLogs",
//                ExistingPeriodicWorkPolicy.KEEP,
//                request
//            )
//            WorkManager.getInstance(this).enqueueUniquePeriodicWork(
//                "sendLogs2",
//                ExistingPeriodicWorkPolicy.KEEP,
//                request
//            )
//            WorkManager.getInstance(this).cancelUniqueWork("sendLogs")
//            WorkManager.getInstance(this).cancelUniqueWork("sendLogs2")
        }
    }
}