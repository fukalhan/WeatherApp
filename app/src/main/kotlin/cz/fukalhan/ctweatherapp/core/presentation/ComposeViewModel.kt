package cz.fukalhan.ctweatherapp.core.presentation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface ComposeViewModel<T, E> {
    val viewState: StateFlow<UiState<T>>

    fun onEvent(event: E)
}

open class PreviewViewModel<T, E >(val state: UiState<T>) : ComposeViewModel<T, E> {

    override val viewState: StateFlow<UiState<T>> = MutableStateFlow(state)

    override fun onEvent(event: E) {}
}