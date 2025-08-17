package cz.fukalhan.ctweatherapp.domain.model

import java.time.LocalDate

data class CachedForecast(
    val fetchedAt: LocalDate,
    val forecasts: WeatherForecast
)