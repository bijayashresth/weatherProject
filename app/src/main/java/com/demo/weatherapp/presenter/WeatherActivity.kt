package com.demo.weatherapp.presenter

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.demo.weatherapp.common.AppConstants
import com.demo.weatherapp.databinding.ActivityWeatherBinding
import com.demo.weatherapp.presenter.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherBinding
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickListener()
        initCall()
        initListeners()
    }

    private fun initClickListener() {
        binding.btnSearch.setOnClickListener {
            if (binding.edtName.text.toString().isEmpty()) {
                Toast.makeText(
                    applicationContext,
                    "Please enter city name to get weather data.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                viewModel.getWeatherData(binding.edtName.text.toString())
                hideKeyboard(it)
            }
        }
    }

    private fun initCall() {
        viewModel.getWeatherData(AppConstants.STATIC_CITY_NAME)
    }

    private fun initListeners() {
        viewModel.errorResponse.observe(this) {
            Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
        }
        viewModel.weatherResponse.observe(this) {
            binding.apply {
                tvCityName.text = it?.name ?: "N/a"
                tvTemperature.text = (it?.main?.temp ?: "N/a") + "\nKelvin"
                tvWind.text = (it?.wind?.speed ?: "N/a") + "km/h"
                if (it?.weather?.isNotEmpty() == true) {
                    val weather = it.weather[0]
                    tvDescription.text = weather.description ?: "N/a"
                    Glide.with(applicationContext)
                        .load("https://openweathermap.org/img/wn/${weather.icon}.png")
                        .into(binding.imgIcon)
                }
            }
        }
    }

    private fun hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}