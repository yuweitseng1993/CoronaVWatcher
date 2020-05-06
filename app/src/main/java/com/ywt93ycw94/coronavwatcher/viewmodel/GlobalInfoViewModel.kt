package com.ywt93ycw94.coronavwatcher.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.ywt93ycw94.coronavwatcher.data.Repository
import com.ywt93ycw94.coronavwatcher.data.model.GlobalInfoResult
import com.ywt93ycw94.coronavwatcher.utils.AppUtils
import kotlinx.coroutines.runBlocking

class GlobalInfoViewModel : ViewModel() {
    private val repository = Repository()
    private val appUtils = AppUtils()

    var newConfirmed = ObservableField("")
    var totalConfirmed = ObservableField("")
    var newDeath = ObservableField("")
    var totalDeath = ObservableField("")
    var newRecover = ObservableField("")
    var totalRecover = ObservableField("")

    fun onFragmentTriggered() {
        runBlocking {
            val globalSummary = repository.retrieveGlobalSummary()
            newConfirmed.set(appUtils.integerFormatter(globalSummary.global.newConfirmed))
            totalConfirmed.set(appUtils.integerFormatter(globalSummary.global.totalConfirmed))
            newDeath.set(appUtils.integerFormatter(globalSummary.global.newDeaths))
            totalDeath.set(appUtils.integerFormatter(globalSummary.global.totalDeaths))
            newRecover.set(appUtils.integerFormatter(globalSummary.global.newRecovered))
            totalRecover.set(appUtils.integerFormatter(globalSummary.global.totalRecovered))
        }

    }

}