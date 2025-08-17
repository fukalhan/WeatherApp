package cz.fukalhan.ctweatherapp.data.repository

import cz.fukalhan.ctweatherapp.data.remote.api.WeatherService
import cz.fukalhan.ctweatherapp.data.remote.mapper.toDomain
import cz.fukalhan.ctweatherapp.domain.model.City
import cz.fukalhan.ctweatherapp.domain.model.WeatherForecast
import cz.fukalhan.ctweatherapp.domain.repository.WeatherRepository
import java.time.LocalDate

class WeatherRepositoryImpl(
    private val weatherService: WeatherService
) : WeatherRepository {

    private val cache = mutableMapOf<Pair<String,String>, List<WeatherForecast>>()

    override suspend fun getForecastForCity(city: City, date: LocalDate): WeatherForecast? {
        val cacheKey = Pair(city.name, date.toString())
        //cache[cacheKey]?.let { return it }

        val response = weatherService.getForecast(
            lat = city.latitude,
            lon = city.longitude,
            startDate = date.toString(),
            endDate = date.plusDays(6).toString(),
            timezone = city.timezone
        )

        val forecast = response.body()?.toDomain(city.name) ?: return null
        /*forecasts.forEach { forecast ->
            cache[Pair(city.name, forecast.date)] = forecast
        }*/
        return forecast
    }
}
