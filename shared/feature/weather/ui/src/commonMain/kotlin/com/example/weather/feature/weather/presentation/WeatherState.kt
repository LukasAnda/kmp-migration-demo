package com.example.weather.feature.weather.presentation

import com.example.weather.feature.weather.domain.model.CityWeather

data class WeatherState(
    val cities: List<CityWeather> = emptyList()
)
