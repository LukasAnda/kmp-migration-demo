package com.example.weather

import android.app.Application
import com.example.weather.app.initializeKoin
import org.koin.android.ext.koin.androidContext

class WeatherApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initializeKoin().apply {
            androidContext(this@WeatherApplication)
        }
    }
}
