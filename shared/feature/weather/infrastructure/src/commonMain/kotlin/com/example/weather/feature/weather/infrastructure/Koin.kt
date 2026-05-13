package com.example.weather.feature.weather.infrastructure

import com.example.weather.feature.weather.domain.WeatherRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val weatherInfrastructureModule = module {
    singleOf(::DefaultWeatherRepository) bind WeatherRepository::class
}
