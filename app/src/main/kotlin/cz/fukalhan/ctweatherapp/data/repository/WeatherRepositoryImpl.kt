package cz.fukalhan.ctweatherapp.data.repository

import cz.fukalhan.ctweatherapp.data.remote.api.WeatherService
import cz.fukalhan.ctweatherapp.data.remote.mapper.toDomain
import cz.fukalhan.ctweatherapp.domain.model.CachedForecast
import cz.fukalhan.ctweatherapp.domain.model.City
import cz.fukalhan.ctweatherapp.domain.model.WeatherForecast
import cz.fukalhan.ctweatherapp.domain.repository.WeatherRepository
import java.time.LocalDate

class WeatherRepositoryImpl(
    private val weatherService: WeatherService
) : WeatherRepository {

    private val cache = mutableMapOf<String, CachedForecast>()

    override suspend fun getForecastForCity(city: City, date: LocalDate): WeatherForecast? {
        val today = LocalDate.now()
        val cached = cache[city.name]

        val isCacheValid = cached != null && cached.fetchedAt == today

        val forecast = if (isCacheValid) {
            cached.forecasts
        } else {
            val response = weatherService.getForecast(
                lat = city.latitude,
                lon = city.longitude,
                startDate = date.toString(),
                endDate = date.plusDays(6).toString(),
                timezone = city.timezone
            )

            val newForecasts = response.body()?.toDomain(city.name) ?: return null
            cache[city.name] = CachedForecast(fetchedAt = today, forecasts = newForecasts)
            newForecasts
        }

        return forecast
    }
}
