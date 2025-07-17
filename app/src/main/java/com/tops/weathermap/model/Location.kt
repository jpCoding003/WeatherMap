package com.tops.weathermap.model


import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("country")
    val country: String,

    @SerializedName("localtime")
    val localtime: String,

    @SerializedName("name")
    val name: String
)