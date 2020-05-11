package com.ywt93ycw94.coronavwatcher.utils

import com.ywt93ycw94.coronavwatcher.data.model.Country

class AppUtils {
    fun integerFormatter(value: Int): String {
        return "%,d".format(value)
    }

    fun topTenCountriesPercentage(topCountries: List<Country>, totalConfirmed: Int):ArrayList<Pair<Float,String>> {
        var topTenDataSet = ArrayList<Pair<Float,String>>()
        var restPercentage = 100.0
        for(t in topCountries) {
            val percentage = (t.totalConfirmed.toFloat()*100.0/totalConfirmed.toFloat()).toFloat()
//            println("DEBUG: $percentage")
            topTenDataSet.add(Pair(percentage, t.countryCode))
            restPercentage -= percentage
        }
        topTenDataSet.add(Pair(restPercentage.toFloat(), "Other Countries"))
        return topTenDataSet
    }
}