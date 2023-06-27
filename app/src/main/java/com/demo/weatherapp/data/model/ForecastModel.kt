package com.demo.weatherapp.data.model

data class ForecastModel(
    val name: String? = null,
    val weather: List<Weather>? = null,
    val main: Main? = null,
    val wind: Wind? = null,
)

data class Main(
    val temp: String? = null,
    val temp_min: String? = null,
    val temp_max: String? = null,
    val pressure: String? = null,
    val humidity: String? = null,
)
data class Wind(
    val speed: String? = null,
)

data class Weather(
    val id: Int? = null,
    val main: String? = null,
    val description: String? = null,
    val icon: String? = null,
)