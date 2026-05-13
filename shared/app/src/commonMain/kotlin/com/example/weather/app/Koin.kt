package com.example.weather.app

import com.example.weather.core.data.remote.coreDataRemoteModule
import com.example.weather.core.di.initKoin
import com.example.weather.core.presentation.corePresentationModule
import com.example.weather.feature.weather.data.remote.weatherDataRemoteModule
import com.example.weather.feature.weather.domain.weatherDomainModule
import com.example.weather.feature.weather.infrastructure.weatherInfrastructureModule
import com.example.weather.feature.weather.presentation.weatherPresentationModule
import org.koin.core.KoinApplication

fun initializeKoin(): KoinApplication {
    return initKoin(
        listOf(
            coreDataRemoteModule,
            corePresentationModule,
            weatherDataRemoteModule,
            weatherDomainModule,
            weatherInfrastructureModule,
            weatherPresentationModule,
        )
    )
}
