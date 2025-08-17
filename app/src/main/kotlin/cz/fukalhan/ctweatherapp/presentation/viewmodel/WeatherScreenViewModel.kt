package cz.fukalhan.ctweatherapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.fukalhan.ctweatherapp.core.presentation.ComposeViewModel
import cz.fukalhan.ctweatherapp.core.presentation.UiState
import cz.fukalhan.ctweatherapp.core.presentation.showLoading
import cz.fukalhan.ctweatherapp.domain.usecase.GetWeatherForecastForCityUseCase
import cz.fukalhan.ctweatherapp.domain.usecase.LoadAvailableCitiesUseCase
import cz.fukalhan.ctweatherapp.presentation.mapper.toWeatherForecastVoList
import cz.fukalhan.ctweatherapp.presentation.model.WeatherForecastVo
import cz.fukalhan.ctweatherapp.presentation.model.WeatherScreenEvent
import cz.fukalhan.ctweatherapp.presentation.model.WeatherScreenVo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

class WeatherScreenViewModel(
    private val loadAvailableCities: LoadAvailableCitiesUseCase,
    private val getWeatherForecastForCity: GetWeatherForecastForCityUseCase
) : ComposeViewModel<WeatherScreenVo, WeatherScreenEvent>,
    ViewModel() {

    private val _viewState = MutableStateFlow(
        UiState(
            data = WeatherScreenVo(),
            isLoading = true
        )
    )

    override val viewState: StateFlow<UiState<WeatherScreenVo>> = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            loadData()
        }
    }

    override fun onEvent(event: WeatherScreenEvent) {
        when(event) {
            WeatherScreenEvent.OnSelectCityClick -> changeBottomSheetVisibility(isVisible = true)
            is WeatherScreenEvent.OnCitySelected -> selectCity(city = event.city)
            WeatherScreenEvent.OnDismissCitiesBottomSheet -> changeBottomSheetVisibility(
                isVisible = false
            )

            is WeatherScreenEvent.OnSelectDate -> changeSelectedDate(date = event.date)
        }
    }

    /**
     * Loads the available cities and updates the view state with the loaded data.
     */
    private suspend fun loadData() {
        _viewState.showLoading()
        val cities = loadAvailableCities()
        _viewState.update {
            UiState(
                data = it.data.copy(
                    availableCities = cities,
                ),
            )
        }
    }

    /**
     * Shows or hides the bottom sheet for city selection based on the [isVisible] parameter.
     */
    private fun changeBottomSheetVisibility(isVisible: Boolean) {
        _viewState.update {
            it.copy(
                data = it.data.copy(
                    showCitiesBottomSheet = isVisible
                )
            )
        }
    }

    /**
     * Selects a city and fetches the weather forecast for the current date.
     * If the forecast is available, it updates the view state with the selected city and its forecast.
     */
    private fun selectCity(city: String) {
        _viewState.showLoading()
        viewModelScope.launch {
            val forecast = getWeatherForecastForCity(city, LocalDate.now())
            if (forecast != null) {
                _viewState.update {
                    UiState(
                        data = it.data.copy(
                            selectedCity = forecast.cityName,
                            forecast = forecast.dailyForecasts.toWeatherForecastVoList()
                        )
                    )
                }
            }
        }
    }

    /**
     * Changes the selected date in the view state.
     * This will update the displayed weather forecast based on the new selected date.
     */
    private fun changeSelectedDate(date: LocalDate) {
        _viewState.update {
            it.copy(
                data = it.data.copy(
                    selectedDate = date
                )
            )
        }
    }
}