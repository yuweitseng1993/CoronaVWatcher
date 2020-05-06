package com.ywt93ycw94.coronavwatcher.utils

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.ywt93ycw94.coronavwatcher.data.model.GlobalInfoResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CoronaStatusService {
    //https://api.covid19api.com/summary
    @GET("summary")
    suspend fun getGlobalSummary(): GlobalInfoResult

    companion object {
        fun create(): CoronaStatusService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.covid19api.com/")
                .build()

            return retrofit.create(CoronaStatusService::class.java)
        }
    }
}