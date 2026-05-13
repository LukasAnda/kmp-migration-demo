package com.example.weather.feature.weather.data.remote

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val weatherDataRemoteModule = module {
    singleOf(::FakeWeatherDataSource)
    singleOf(::CityMapper)
    singleOf(::ForecastMapper)
}
