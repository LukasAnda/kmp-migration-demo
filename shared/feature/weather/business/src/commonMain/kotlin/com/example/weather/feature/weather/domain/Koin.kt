package com.example.weather.feature.weather.domain

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val weatherDomainModule = module {
    singleOf(::WeatherService)
}
