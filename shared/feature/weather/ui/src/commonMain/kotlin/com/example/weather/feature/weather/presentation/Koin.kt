package com.example.weather.feature.weather.presentation

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val weatherPresentationModule = module {
    viewModelOf(::WeatherViewModel)
}
