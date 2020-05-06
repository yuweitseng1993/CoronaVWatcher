package com.ywt93ycw94.coronavwatcher.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GlobalInfoResult (
    @SerializedName("Global")
    @Expose
    val global: Global,
    @SerializedName("Countries")
    val countries: ArrayList<Country>,
    @SerializedName("Date")
    @Expose
    val date: String
): Parcelable