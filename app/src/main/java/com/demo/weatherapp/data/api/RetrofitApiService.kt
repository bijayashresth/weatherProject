package com.demo.weatherapp.data.api

import com.demo.weatherapp.data.model.ForecastModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApiService {

    @GET("weather/")
    suspend fun getWeatherData(
        @Query("q") cityName: String,
        @Query("appid") appID: String,
    ): Response<ForecastModel>

}