package cz.fukalhan.ctweatherapp.domain.model

/**
 * Represents a city with its geographical and timezone information.
 *
 * @param name The name of the city.
 * @param latitude The latitude coordinate of the city.
 * @param longitude The longitude coordinate of the city.
 * @param timezone The timezone of the city.
 */
data class City(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val timezone: String
)