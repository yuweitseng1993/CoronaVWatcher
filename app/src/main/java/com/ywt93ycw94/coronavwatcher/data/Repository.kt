package com.ywt93ycw94.coronavwatcher.data

import com.ywt93ycw94.coronavwatcher.utils.CoronaStatusService

class Repository {
    private val coronaStatusService by lazy { CoronaStatusService.create() }

    suspend fun retrieveGlobalSummary() = coronaStatusService.getGlobalSummary()
}