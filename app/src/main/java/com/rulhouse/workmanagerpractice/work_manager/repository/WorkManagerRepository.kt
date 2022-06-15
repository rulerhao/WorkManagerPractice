package com.rulhouse.workmanagerpractice.work_manager.repository

import androidx.annotation.NonNull
import androidx.work.ListenableWorker

interface WorkManagerRepository {
    fun setWork(workerClass: Class<out ListenableWorker>)

    fun setPeriodicWork()

    fun cancelPeriodicWork()
}