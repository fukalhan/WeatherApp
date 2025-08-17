package cz.fukalhan.ctweatherapp.core.presentation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Interface representing a ViewModel for Compose UI.
 *
 * @param T The type of data to be displayed in the UI.
 * @param E The type of events that can be handled by the ViewModel.
 */
interface ComposeViewModel<T, E> {
    val viewState: StateFlow<UiState<T>>

    fun onEvent(event: E)
}

open class PreviewViewModel<T, E >(val state: UiState<T>) : ComposeViewModel<T, E> {

    override val viewState: StateFlow<UiState<T>> = MutableStateFlow(state)

    override fun onEvent(event: E) {}
}