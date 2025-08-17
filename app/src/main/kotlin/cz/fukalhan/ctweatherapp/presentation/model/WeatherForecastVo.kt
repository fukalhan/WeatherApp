package cz.fukalhan.ctweatherapp.presentation.model

import java.time.LocalDate

/**
 * Represents a weather forecast for a specific date.
 *
 * @param date The date of the forecast.
 * @param minTemp The minimum temperature expected on that date.
 * @param maxTemp The maximum temperature expected on that date.
 */
data class WeatherForecastVo(
    val date: LocalDate,
    val minTemp: Double,
    val maxTemp: Double
)