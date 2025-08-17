package cz.fukalhan.ctweatherapp.domain.repository

import cz.fukalhan.ctweatherapp.domain.model.City
import cz.fukalhan.ctweatherapp.domain.model.WeatherForecast
import java.time.LocalDate

/**
 * Interface for a repository that provides weather forecast data.
 */
interface WeatherRepository {

    /**
     * Retrieves the weather forecast for a specific city on a given date.
     *
     * @param city The city for which the forecast is requested.
     * @param date The date for which the forecast is requested.
     * @return The weather forecast for the specified city and date, or null if not available.
     */
    suspend fun getForecastForCity(city: City, date: LocalDate): WeatherForecast?
}
