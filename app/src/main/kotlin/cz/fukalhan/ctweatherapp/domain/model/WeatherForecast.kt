package cz.fukalhan.ctweatherapp.domain.model

/**
 * Represents a weather forecast for a city, including daily forecasts.
 *
 * @param cityName The name of the city for which the forecast is provided.
 * @param dailyForecasts A list of daily forecasts containing date and temperature information.
 */
data class WeatherForecast(
    val cityName: String,
    val dailyForecasts: List<DailyForecast>
)

/**
 * Represents a daily weather forecast with minimum and maximum temperatures.
 *
 * @param date The date of the forecast.
 * @param minTemp The minimum temperature expected on that date.
 * @param maxTemp The maximum temperature expected on that date.
 */
data class DailyForecast(
    val date: String,
    val minTemp: Double,
    val maxTemp: Double
)