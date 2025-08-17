package cz.fukalhan.ctweatherapp.domain.model

data class City(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val timezone: String
)