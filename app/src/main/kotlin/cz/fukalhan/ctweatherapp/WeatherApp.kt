package cz.fukalhan.ctweatherapp

import android.app.Application
import cz.fukalhan.ctweatherapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class WeatherApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WeatherApp)
            modules(listOf(appModule))
        }
    }
}
