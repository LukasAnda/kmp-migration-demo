package com.example.weather.feature.weather.presentation

import com.example.weather.core.domain.DispatcherProvider
import com.example.weather.core.presentation.BaseViewModel
import com.example.weather.feature.weather.domain.WeatherService

class WeatherViewModel(
    private val weatherService: WeatherService,
    dispatcherProvider: DispatcherProvider
) : BaseViewModel<WeatherState>(WeatherState(), dispatcherProvider) {

    init {
        loadWeather()
    }

    fun loadWeather() {
        launchWithResult(
            block = { weatherService.getWeatherForAllCities() },
            onSuccess = { cities ->
                setSuccess(WeatherState(cities = cities))
            }
        )
    }

    fun refresh() {
        loadWeather()
    }
}
