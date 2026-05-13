package com.example.weather.feature.weather.data.remote

import com.example.weather.feature.weather.data.remote.dto.CityDto
import com.example.weather.feature.weather.data.remote.dto.ForecastDto
import kotlinx.coroutines.delay

class FakeWeatherDataSource {

    suspend fun getCities(): List<CityDto> {
        delay(500)
        return listOf(
            CityDto("1", "Bratislava", "SK"),
            CityDto("2", "Prague", "CZ"),
            CityDto("3", "Vienna", "AT"),
            CityDto("4", "Budapest", "HU"),
            CityDto("5", "Warsaw", "PL"),
            CityDto("6", "Berlin", "DE"),
            CityDto("7", "Amsterdam", "NL"),
            CityDto("8", "Paris", "FR")
        )
    }

    suspend fun getForecast(cityId: String): ForecastDto {
        delay(200)
        return when (cityId) {
            "1" -> ForecastDto(22, "Sunny", 45)
            "2" -> ForecastDto(19, "Cloudy", 60)
            "3" -> ForecastDto(24, "Sunny", 40)
            "4" -> ForecastDto(26, "Clear", 35)
            "5" -> ForecastDto(17, "Rainy", 80)
            "6" -> ForecastDto(18, "Overcast", 65)
            "7" -> ForecastDto(15, "Rainy", 85)
            "8" -> ForecastDto(21, "Partly Cloudy", 55)
            else -> ForecastDto(20, "Unknown", 50)
        }
    }
}
