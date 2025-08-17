package cz.fukalhan.ctweatherapp.data.remote.api

import cz.fukalhan.ctweatherapp.data.remote.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("forecast")
    suspend fun getForecast(
        @Query("latitude") lat: Double,
        @Query("longitude") lon: Double,
        @Query("daily") daily: String = "temperature_2m_min,temperature_2m_max",
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("timezone") timezone: String,
        @Query("temperature_unit") tempUnit: String = "celsius"
    ): Response<WeatherResponse>

}