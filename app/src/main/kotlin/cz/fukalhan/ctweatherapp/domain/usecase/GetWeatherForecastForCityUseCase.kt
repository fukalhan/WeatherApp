package cz.fukalhan.ctweatherapp.domain.usecase

import cz.fukalhan.ctweatherapp.domain.model.WeatherForecast
import cz.fukalhan.ctweatherapp.domain.repository.CityRepository
import cz.fukalhan.ctweatherapp.domain.repository.WeatherRepository
import java.time.LocalDate

/**
 * Use case for retrieving the weather forecast for a specific city on a given date.
 *
 * @property cityRepository Repository for resolving city information.
 * @property weatherRepository Repository for fetching weather data.
 */
class GetWeatherForecastForCityUseCase(
    private val cityRepository: CityRepository,
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(cityName: String, date: LocalDate): WeatherForecast? {
        val city = cityRepository.resolveCity(cityName) ?: return null
        return weatherRepository.getForecastForCity(city, date)
    }
}