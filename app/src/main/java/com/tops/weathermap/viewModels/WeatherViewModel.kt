package com.tops.weathermap.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tops.weathermap.WeatherRepository
import com.tops.weathermap.model.Weather
import kotlinx.coroutines.launch

class WeatherViewModel: ViewModel() {

    private val _weatherData = MutableLiveData<Weather?>()
    val weatherData: LiveData<Weather?> = _weatherData

    fun loadData(apiKey: String, city: String = "Surat"){
        viewModelScope.launch {
            val weather = WeatherRepository.getWeatherData(apiKey,city)
            _weatherData.value = weather
        }
    }

}