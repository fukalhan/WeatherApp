package cz.fukalhan.ctweatherapp.data.repository

import cz.fukalhan.ctweatherapp.data.remote.api.GeocodingService
import cz.fukalhan.ctweatherapp.data.remote.mapper.toDomain
import cz.fukalhan.ctweatherapp.domain.model.City
import cz.fukalhan.ctweatherapp.domain.repository.CityRepository

class CityRepositoryImpl(
    private val geocodingService: GeocodingService
) : CityRepository {

    override suspend fun getAvailableCities(): List<String> {
        return listOf("Prague", "New York", "London")
    }

    override suspend fun resolveCity(name: String): City? {
        return geocodingService.searchCity(name).body()?.results?.firstOrNull()?.toDomain()
    }
}
