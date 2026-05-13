package com.example.weather.core.presentation

import com.example.weather.core.domain.DefaultDispatcherProvider
import com.example.weather.core.domain.DispatcherProvider
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val corePresentationModule = module {
    singleOf(::DefaultDispatcherProvider) bind DispatcherProvider::class
}
