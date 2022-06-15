package com.rulhouse.workmanagerpractice

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.rulhouse.workmanagerpractice.worker.original_worker.MyOriginalWorker
import com.rulhouse.workmanagerpractice.work_manager.use_cases.WorkManagerUseCases
import com.rulhouse.workmanagerpractice.worker.coroutine_work.MyWorker
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var workManagerUseCases: WorkManagerUseCases

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        workManagerUseCases.setPeriodicWork()
        workManagerUseCases.setWork(MyWorker::class.java)
//        workManagerUseCases.setWork(MyOriginalWorker::class.java)
    }
}