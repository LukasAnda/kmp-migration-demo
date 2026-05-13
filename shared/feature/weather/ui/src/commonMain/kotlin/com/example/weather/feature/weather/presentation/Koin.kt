package com.example.weather.feature.weather.presentation

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val weatherPresentationModule = module {
    viewModel { WeatherViewModel(weatherService = get(), dispatcherProvider = get()) }
}
