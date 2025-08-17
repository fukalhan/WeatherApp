package cz.fukalhan.ctweatherapp.domain.model

data class WeatherForecast(
    val cityName: String,
    val dailyForecasts: List<DailyForecast>
)

data class DailyForecast(
    val date: String,
    val minTemp: Double,
    val maxTemp: Double
)