package cz.fukalhan.ctweatherapp.presentation.mapper

import cz.fukalhan.ctweatherapp.domain.model.DailyForecast
import cz.fukalhan.ctweatherapp.presentation.model.WeatherForecastVo
import java.time.LocalDate

internal fun List<DailyForecast>.toWeatherForecastVoList(): List<WeatherForecastVo> {
    return map { dailyForecast ->
        WeatherForecastVo(
            date = LocalDate.parse(dailyForecast.date),
            minTemp = dailyForecast.minTemp,
            maxTemp = dailyForecast.maxTemp
        )
    }
}