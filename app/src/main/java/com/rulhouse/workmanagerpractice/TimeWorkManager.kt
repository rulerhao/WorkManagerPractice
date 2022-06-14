package com.rulhouse.workmanagerpractice

import androidx.work.Constraints
import androidx.work.NetworkType

object TimeWorkManager {
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()
}