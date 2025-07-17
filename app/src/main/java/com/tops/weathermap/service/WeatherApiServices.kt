package com.tops.weathermap.service

import com.tops.weathermap.model.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiServices {

    @GET("current")
    suspend fun getCurrentWeather( @Query("access_key") apiKey: String, @Query("query") cityName: String): Response<Weather>
}