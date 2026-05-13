package com.example.weather.feature.weather.infrastructure

import com.example.weather.feature.weather.domain.WeatherRepository
import org.koin.dsl.bind
import org.koin.dsl.module

val weatherInfrastructureModule = module {
    single {
        DefaultWeatherRepository(
            dataSource = get(),
            cityMapper = get(),
            forecastMapper = get()
        )
    } bind WeatherRepository::class
}
