package ru.samitin.weather.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.samitin.weather.koin.application
import ru.samitin.weather.koin.mainScreen

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen))
        }
    }

}