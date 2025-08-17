package cz.fukalhan.ctweatherapp.data.remote

import cz.fukalhan.ctweatherapp.data.remote.api.GeocodingService
import cz.fukalhan.ctweatherapp.data.remote.api.WeatherService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retrofit client for making network requests to weather and geocoding APIs.
 */
object RetrofitClient {
    private const val WEATHER_BASE_URL = "https://api.open-meteo.com/v1/"
    private const val GEOCODING_BASE_URL = "https://geocoding-api.open-meteo.com/v1/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    /**
     * Weather API service for fetching weather data.
     */
    val weatherApi: WeatherService = Retrofit.Builder()
        .baseUrl(WEATHER_BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherService::class.java)

    /**
     * Geocoding API service for searching cities.
     */
    val geocodingApi: GeocodingService = Retrofit.Builder()
        .baseUrl(GEOCODING_BASE_URL)
        .client(httpClient) // same client
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GeocodingService::class.java)
}