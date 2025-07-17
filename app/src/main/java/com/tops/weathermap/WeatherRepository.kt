package com.tops.weathermap

import android.widget.Toast
import com.tops.weathermap.model.Weather
import com.tops.weathermap.service.RequestApiService

object WeatherRepository {
    private var weatherdata: Weather? = null

    suspend fun getWeatherData(apiKey: String, city: String): Weather?{

            val response = RequestApiService.api.getCurrentWeather(apiKey,city)
            if (response.isSuccessful){
                weatherdata = response.body()
            }else {
                // Optionally log or throw exception
            }
        return weatherdata
    }
}