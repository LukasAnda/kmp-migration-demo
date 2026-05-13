package com.example.weather.core.data.remote

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val coreDataRemoteModule = module {
    singleOf(::HttpClientFactory)
    single { get<HttpClientFactory>().create() }
}
