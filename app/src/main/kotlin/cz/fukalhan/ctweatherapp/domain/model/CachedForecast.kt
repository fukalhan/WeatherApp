package cz.fukalhan.ctweatherapp.domain.model

import java.time.LocalDate

/**
 * Represents a cached weather forecast for a specific date.
 *
 * @param fetchedAt The date when the forecast was fetched.
 * @param forecasts The weather forecast data.
 */
data class CachedForecast(
    val fetchedAt: LocalDate,
    val forecasts: WeatherForecast
)