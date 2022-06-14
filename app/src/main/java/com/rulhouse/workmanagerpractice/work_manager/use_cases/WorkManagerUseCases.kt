package com.rulhouse.workmanagerpractice.work_manager.use_cases

data class WorkManagerUseCases (
    val setPeriodicWork: SetPeriodicWork,
    val cancelPeriodicWork: CancelPeriodicWork
)