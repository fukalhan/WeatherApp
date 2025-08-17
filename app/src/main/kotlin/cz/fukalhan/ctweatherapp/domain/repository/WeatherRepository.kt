package cz.fukalhan.ctweatherapp.domain.repository

import cz.fukalhan.ctweatherapp.domain.model.City
import cz.fukalhan.ctweatherapp.domain.model.WeatherForecast
import java.time.LocalDate

interface WeatherRepository {
    suspend fun getForecastForCity(city: City, date: LocalDate): WeatherForecast?
}
