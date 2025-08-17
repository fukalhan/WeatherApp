package cz.fukalhan.ctweatherapp.domain.repository

import cz.fukalhan.ctweatherapp.domain.model.City

interface CityRepository {
    suspend fun getAvailableCities(): List<String>

    suspend fun resolveCity(name: String): City?
}
