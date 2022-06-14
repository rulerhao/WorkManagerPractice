package com.rulhouse.workmanagerpractice.work_manager.repository

interface WorkManagerRepository {
    fun setPeriodicWork()

    fun cancelPeriodicWork()
}