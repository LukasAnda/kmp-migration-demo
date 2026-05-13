package com.example.weather.feature.weather.data.remote

import org.koin.dsl.module

val weatherDataRemoteModule = module {
    single { FakeWeatherDataSource() }
    single { CityMapper() }
    single { ForecastMapper() }
}
