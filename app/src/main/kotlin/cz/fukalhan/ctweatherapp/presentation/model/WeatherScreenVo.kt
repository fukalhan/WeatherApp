package cz.fukalhan.ctweatherapp.presentation.model

import java.time.LocalDate

/**
 * Represents the state of the weather screen in the application.
 *
 * @property availableCities List of cities for which weather data is available.
 * @property selectedCity The currently selected city for which the weather forecast is displayed.
 * @property forecast List of weather forecasts for the selected city.
 * @property selectedDate The date for which the weather forecast is displayed.
 * @property showCitiesBottomSheet Flag indicating whether to show the bottom sheet with available cities.
 */
data class WeatherScreenVo(
    val availableCities: List<String> = emptyList(),
    val selectedCity: String? = null,
    val forecast: List<WeatherForecastVo> = emptyList(),
    val selectedDate: LocalDate? = null,
    val showCitiesBottomSheet: Boolean = false,
) {
    fun getSelectedDateIndex(): Int {
        return selectedDate?.let { date ->
            forecast.indexOfFirst { it.date == date }
        } ?: 0
    }
}