package cz.fukalhan.ctweatherapp.data.remote.api

import cz.fukalhan.ctweatherapp.data.remote.model.GeocodingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingService {
    @GET("search")
    suspend fun searchCity(
        @Query("name") name: String,
        @Query("count") count: Int = 1
    ): Response<GeocodingResponse>
}