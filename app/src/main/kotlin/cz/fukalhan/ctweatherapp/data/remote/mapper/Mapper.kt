package cz.fukalhan.ctweatherapp.data.remote.mapper

import cz.fukalhan.ctweatherapp.data.remote.model.GeocodingResultDto
import cz.fukalhan.ctweatherapp.data.remote.model.WeatherResponse
import cz.fukalhan.ctweatherapp.domain.model.City
import cz.fukalhan.ctweatherapp.domain.model.DailyForecast
import cz.fukalhan.ctweatherapp.domain.model.WeatherForecast

fun GeocodingResultDto.toDomain(): City {
    return City(
        name = name,
        latitude = latitude,
        longitude = longitude,
        timezone = timezone
    )
}

fun WeatherResponse.toDomain(cityName: String): WeatherForecast {
    return WeatherForecast(
        cityName = cityName,
        dailyForecasts = getDailyForecasts(
            time = daily.time,
            minTemp = daily.minTemps,
            maxTemp = daily.maxTemps
        )
    )
}

private fun getDailyForecasts(
    time: List<String>,
    minTemp: List<Double>,
    maxTemp: List<Double>,
): List<DailyForecast> {
    return time.mapIndexed { index, date ->
        DailyForecast(
            date = date,
            minTemp = minTemp.getOrNull(index) ?: 0.0,
            maxTemp = maxTemp.getOrNull(index) ?: 0.0
        )
    }
}
