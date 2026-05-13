package com.example.weather.core.domain

sealed class AppError(open val message: String) {
    data class Network(override val message: String) : AppError(message)
    data class Unknown(override val message: String) : AppError(message)
}
