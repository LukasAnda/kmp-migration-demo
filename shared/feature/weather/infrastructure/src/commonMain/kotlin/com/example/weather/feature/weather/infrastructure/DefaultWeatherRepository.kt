package com.example.weather.feature.weather.infrastructure

import com.example.weather.core.domain.Result
import com.example.weather.core.domain.runCatching as runResultCatching
import com.example.weather.feature.weather.data.remote.CityMapper
import com.example.weather.feature.weather.data.remote.FakeWeatherDataSource
import com.example.weather.feature.weather.data.remote.ForecastMapper
import com.example.weather.feature.weather.domain.WeatherRepository
import com.example.weather.feature.weather.domain.model.City
import com.example.weather.feature.weather.domain.model.Forecast

class DefaultWeatherRepository(
    private val dataSource: FakeWeatherDataSource,
    private val cityMapper: CityMapper,
    private val forecastMapper: ForecastMapper
) : WeatherRepository {

    override suspend fun getCities(): Result<List<City>> {
        return runResultCatching {
            cityMapper.mapList(dataSource.getCities())
        }
    }

    override suspend fun getForecast(cityId: String): Result<Forecast> {
        return runResultCatching {
            forecastMapper.map(dataSource.getForecast(cityId))
        }
    }
}
