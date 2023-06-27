package com.demo.weatherapp.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.weatherapp.data.model.ForecastModel
import com.demo.weatherapp.data.repository.WeatherDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel
@Inject constructor(private val repository: WeatherDataRepository) : ViewModel() {

    private val _weatherResponse = MutableLiveData<ForecastModel>()
    val weatherResponse: LiveData<ForecastModel>
        get() = _weatherResponse

    private val _error = MutableLiveData<String>()
    val errorResponse: LiveData<String>
        get() = _error

    fun getWeatherData(cityName: String) = viewModelScope.launch {
        repository.getWeatherData(cityName).let { response ->
            if (response.isSuccessful) {
                _weatherResponse.postValue(response.body())
            } else {
                _error.postValue("Error :  ${response.message()}")
            }
        }
    }
}













