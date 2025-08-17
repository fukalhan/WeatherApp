package cz.fukalhan.ctweatherapp.di

import cz.fukalhan.ctweatherapp.data.remote.RetrofitClient
import cz.fukalhan.ctweatherapp.data.repository.CityRepositoryImpl
import cz.fukalhan.ctweatherapp.data.repository.WeatherRepositoryImpl
import cz.fukalhan.ctweatherapp.domain.repository.CityRepository
import cz.fukalhan.ctweatherapp.domain.repository.WeatherRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import cz.fukalhan.ctweatherapp.domain.usecase.GetWeatherForecastForCityUseCase
import cz.fukalhan.ctweatherapp.domain.usecase.LoadAvailableCitiesUseCase
import cz.fukalhan.ctweatherapp.presentation.viewmodel.WeatherScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf

val appModule = module {
    viewModelOf(::WeatherScreenViewModel)

    single<CityRepository> {
        CityRepositoryImpl(
            geocodingService = get()
        )
    }

    single<WeatherRepository> {
        WeatherRepositoryImpl(
            weatherService = get()
        )
    }

    single { RetrofitClient.weatherApi }
    single { RetrofitClient.geocodingApi }

    factoryOf(::GetWeatherForecastForCityUseCase)
    factoryOf(::LoadAvailableCitiesUseCase)
}