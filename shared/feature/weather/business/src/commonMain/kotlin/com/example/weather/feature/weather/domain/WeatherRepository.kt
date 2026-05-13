package com.example.weather.feature.weather.domain

import com.example.weather.core.domain.Result
import com.example.weather.feature.weather.domain.model.City
import com.example.weather.feature.weather.domain.model.Forecast

interface WeatherRepository {
    suspend fun getCities(): Result<List<City>>
    suspend fun getForecast(cityId: String): Result<Forecast>
}
