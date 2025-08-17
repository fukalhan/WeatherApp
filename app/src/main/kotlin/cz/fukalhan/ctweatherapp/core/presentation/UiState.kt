package cz.fukalhan.ctweatherapp.core.presentation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

/**
 * Represents the UI state of a screen or component.
 *
 * @param T The type of data being represented in the UI state.
 * @property data The data to be displayed in the UI.
 * @property isLoading Indicates whether the data is currently being loaded.
 */
data class UiState<T>(
    val data: T,
    val isLoading: Boolean = false,
)


fun <T> MutableStateFlow<UiState<T>>.showLoading() =
    update { state ->
        UiState(
            data = state.data,
            isLoading = true,
        )
    }