package cz.fukalhan.ctweatherapp.presentation.model

import java.time.LocalDate

sealed interface WeatherScreenEvent {

    data object OnSelectCityClick : WeatherScreenEvent

    data class OnCitySelected(val city: String) : WeatherScreenEvent

    data object OnDismissCitiesBottomSheet : WeatherScreenEvent

    data class OnSelectDate(val date: LocalDate) : WeatherScreenEvent
}