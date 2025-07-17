package com.tops.weathermap.model


import com.google.gson.annotations.SerializedName

data class Current(
    @SerializedName("astro")
    val astro: Astro,
    val feelslike: Int,
    @SerializedName("humidity")
    val humidity: Int,

    @SerializedName("temperature")
    val temperature: Int,

    @SerializedName("weather_descriptions")
    val weatherDescriptions: List<String>,
    @SerializedName("wind_speed")
    val windSpeed: Int
)