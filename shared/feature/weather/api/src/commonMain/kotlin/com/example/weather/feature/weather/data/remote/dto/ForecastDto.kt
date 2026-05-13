package com.example.weather.feature.weather.data.remote.dto

data class ForecastDto(
    val temperature: Int,
    val condition: String,
    val humidity: Int
)
