package com.ywt93ycw94.coronavwatcher.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ywt93ycw94.coronavwatcher.data.Repository
import com.ywt93ycw94.coronavwatcher.data.model.Country
import com.ywt93ycw94.coronavwatcher.data.model.GlobalInfoResult
import com.ywt93ycw94.coronavwatcher.utils.AppUtils
import kotlinx.coroutines.runBlocking

class GlobalInfoViewModel : ViewModel() {
    private val repository = Repository()
    private val appUtils = AppUtils()
    private lateinit var globalSummary: GlobalInfoResult

    var newConfirmed = ObservableField("")
    var totalConfirmed = ObservableField("")
    var newDeath = ObservableField("")
    var totalDeath = ObservableField("")
    var newRecover = ObservableField("")
    var totalRecover = ObservableField("")
    var topTenCountries = MutableLiveData<List<Country>>()

    fun onFragmentTriggered() {
        runBlocking {
            globalSummary = repository.retrieveGlobalSummary()
        }
        updateGlobalStatus()
        determineTopTenCountries(globalSummary.countries)
    }

    private fun updateGlobalStatus() {
        newConfirmed.set(appUtils.integerFormatter(globalSummary.global.newConfirmed))
        totalConfirmed.set(appUtils.integerFormatter(globalSummary.global.totalConfirmed))
        newDeath.set(appUtils.integerFormatter(globalSummary.global.newDeaths))
        totalDeath.set(appUtils.integerFormatter(globalSummary.global.totalDeaths))
        newRecover.set(appUtils.integerFormatter(globalSummary.global.newRecovered))
        totalRecover.set(appUtils.integerFormatter(globalSummary.global.totalRecovered))
    }

    private fun determineTopTenCountries(countryStats: ArrayList<Country>) {
        var sortedList = countryStats.sortedWith(compareByDescending {it.totalConfirmed})
//        for (obj in sortedList) {
//            println("DEBUG: ${obj.country} : ${obj.totalConfirmed}")
//        }
        topTenCountries.value = sortedList.subList(0,10)
    }
}