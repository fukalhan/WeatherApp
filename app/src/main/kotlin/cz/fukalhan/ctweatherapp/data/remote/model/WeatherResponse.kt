package cz.fukalhan.ctweatherapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    val daily: DailyWeatherDto
)

data class DailyWeatherDto(
    val time: List<String>,
    @SerializedName("temperature_2m_min")
    val minTemps: List<Double>,
    @SerializedName("temperature_2m_max")
    val maxTemps: List<Double>
)