package cz.fukalhan.ctweatherapp.domain.repository

import cz.fukalhan.ctweatherapp.domain.model.City

/**
 * Repository interface for managing city data.
 *
 * This interface defines methods to retrieve available cities and resolve a city by its name.
 */
interface CityRepository {

    /**
     * Retrieves a list of available cities.
     *
     * @return A list of city names.
     */
    suspend fun getAvailableCities(): List<String>

    /**
     * Resolves a city by its name.
     *
     * @param name The name of the city to resolve.
     * @return A [City] object if found, or null if the city does not exist.
     */
    suspend fun resolveCity(name: String): City?
}
