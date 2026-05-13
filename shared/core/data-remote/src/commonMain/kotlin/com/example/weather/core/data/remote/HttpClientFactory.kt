package com.example.weather.core.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig

expect fun createHttpClient(config: HttpClientConfig<*>.() -> Unit = {}): HttpClient

class HttpClientFactory {
    fun create(): HttpClient = createHttpClient()
}
