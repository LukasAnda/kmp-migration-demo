package com.example.weather.feature.weather.domain

import com.example.weather.core.domain.DispatcherProvider
import com.example.weather.core.domain.Result
import com.example.weather.feature.weather.domain.model.CityWeather
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

class WeatherService(
    private val repository: WeatherRepository,
    private val dispatcherProvider: DispatcherProvider
) {
    suspend fun getWeatherForAllCities(): Result<List<CityWeather>> {
        return withContext(dispatcherProvider.io) {
            when (val citiesResult = repository.getCities()) {
                is Result.Error -> citiesResult
                is Result.Success -> {
                    val weatherList = citiesResult.data.map { city ->
                        async {
                            when (val forecastResult = repository.getForecast(city.id)) {
                                is Result.Success -> CityWeather(city, forecastResult.data)
                                is Result.Error -> null
                            }
                        }
                    }.awaitAll().filterNotNull()
                    Result.Success(weatherList)
                }
            }
        }
    }
}
