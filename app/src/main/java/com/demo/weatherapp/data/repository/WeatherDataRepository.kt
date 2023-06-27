package com.demo.weatherapp.data.repository

import com.demo.weatherapp.data.api.RetrofitApiService
import com.demo.weatherapp.common.AppConstants
import javax.inject.Inject

class WeatherDataRepository
@Inject constructor(private val retrofitApiService: RetrofitApiService) {
    suspend fun getWeatherData(cityName: String) = retrofitApiService.getWeatherData(cityName, AppConstants.API_KEY)
}