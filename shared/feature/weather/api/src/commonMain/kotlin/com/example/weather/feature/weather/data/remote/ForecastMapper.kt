package com.example.weather.feature.weather.data.remote

import com.example.weather.feature.weather.data.remote.dto.ForecastDto
import com.example.weather.feature.weather.domain.model.Forecast

class ForecastMapper {
    fun map(dto: ForecastDto): Forecast = Forecast(
        temperature = dto.temperature,
        condition = dto.condition,
        humidity = dto.humidity
    )
}
