package com.tops.weathermap

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tops.weathermap.databinding.ActivityMainBinding
import com.tops.weathermap.viewModels.WeatherViewModel


//  API
//  https://api.weatherstack.com/current?access_key={PASTE_YOUR_API_KEY_HERE}&query=New Delhi
//  key = 2ce83840db1eff428341f59678372aa1

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private  val weatherviewmodel: WeatherViewModel by viewModels()
    private val apikey = "2ce83840db1eff428341f59678372aa1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        weatherviewmodel.loadData(apikey, "Surat")
        observeWeatherData()

        binding.seachView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null){
                    weatherviewmodel.loadData(apikey,query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun observeWeatherData() {
        weatherviewmodel.weatherData.observe(this) { weather ->
            if (weather != null) {
                binding.tvCityName.text = weather.location?.name
                binding.tvWeather.text = weather.current?.weatherDescriptions?.firstOrNull()
                binding.tvTemperature.text = "${weather.current?.temperature?.toInt()}"
                binding.tvConditonOption.text = weather.current?.weatherDescriptions?.joinToString(", ")
                binding.tvCurrentDate.text = weather.location?.localtime
                binding.tvWindSpeedOption.text = "${weather.current?.windSpeed} km/h"
                binding.tvHumidityOption.text = "${weather.current?.humidity} %"

                // No sunrise/sunset from current API
                binding.tvSunRiseOption.text = "N/A"
                binding.tvSunSetOption.text = "N/A"

                setWeatherUI(weather.current?.temperature)
            } else {
                Toast.makeText(this, "No weather data found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setWeatherUI(temp: Int?) {
        if (temp == null) return
        when {
            temp <= 4 -> setBackground(R.drawable.snow_background, R.raw.snow)
            temp in 5..25 -> setBackground(R.drawable.colud_background, R.raw.cloud)
            temp in 26..30 -> setBackground(R.drawable.rain_background, R.raw.rain)
            else -> setBackground(R.drawable.sunny_background, R.raw.sun)
        }
    }

    private fun setBackground(backgroundRes: Int, lottieRes: Int) {
        binding.main.setBackgroundResource(backgroundRes)
        binding.lottieAnimationView.setAnimation(lottieRes)
        binding.lottieAnimationView.playAnimation()
    }
}