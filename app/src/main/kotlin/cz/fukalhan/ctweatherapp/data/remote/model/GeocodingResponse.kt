package cz.fukalhan.ctweatherapp.data.remote.model

data class GeocodingResponse(
    val results: List<GeocodingResultDto>?
)

data class GeocodingResultDto(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val country: String,
    val timezone: String
)