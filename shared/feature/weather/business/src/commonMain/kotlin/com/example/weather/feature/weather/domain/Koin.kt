package com.example.weather.feature.weather.domain

import org.koin.dsl.module

val weatherDomainModule = module {
    single { WeatherService(repository = get(), dispatcherProvider = get()) }
}
