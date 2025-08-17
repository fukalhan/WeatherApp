package cz.fukalhan.ctweatherapp.presentation.model

import java.time.LocalDate

/**
 * Represents events that can occur on the weather screen.
 */
sealed interface WeatherScreenEvent {

    /**
     * Event triggered when the user clicks on the city selector.
     */
    data object OnSelectCityClick : WeatherScreenEvent

    /**
     * Event triggered when the user selects a city from the list.
     */
    data class OnCitySelected(val city: String) : WeatherScreenEvent

    /**
     * Event triggered when the user closes the bottom sheet for selecting cities.
     */
    data object OnDismissCitiesBottomSheet : WeatherScreenEvent

    /**
     * Event triggered when the user selects a date from the date picker.
     */
    data class OnSelectDate(val date: LocalDate) : WeatherScreenEvent
}