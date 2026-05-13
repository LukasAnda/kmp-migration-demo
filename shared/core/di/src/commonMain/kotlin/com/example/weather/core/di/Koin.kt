package com.example.weather.core.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module

expect fun platformModule(): Module

fun initKoin(appModules: List<Module>): KoinApplication {
    return startKoin {
        modules(appModules + platformModule())
    }
}
