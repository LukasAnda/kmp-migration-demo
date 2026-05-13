package com.example.weather.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.weather.feature.weather.presentation.WeatherScreen
import com.example.weather.feature.weather.presentation.WeatherViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    MaterialTheme {
        val viewModel: WeatherViewModel = koinViewModel()
        WeatherScreen(viewModel = viewModel)
    }
}
