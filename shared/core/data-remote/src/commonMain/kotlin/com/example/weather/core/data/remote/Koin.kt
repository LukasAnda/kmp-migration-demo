package com.example.weather.core.data.remote

import org.koin.dsl.module

val coreDataRemoteModule = module {
    single { HttpClientFactory() }
    single { get<HttpClientFactory>().create() }
}
