package com.tops.weathermap.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RequestApiService  {

    private val retrofit  by lazy {
        Retrofit.Builder().baseUrl("https://api.weatherstack.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: WeatherApiServices by lazy {
        retrofit.create(WeatherApiServices::class.java)
    }

}