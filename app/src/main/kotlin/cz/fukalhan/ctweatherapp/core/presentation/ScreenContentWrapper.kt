package cz.fukalhan.ctweatherapp.core.presentation

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * A composable function that wraps the content of a screen and displays a loading indicator
 */
@Composable
fun ScreenContentWrapper(
    state: UiState<*>,
    content: @Composable () -> Unit,
) {
    content()
    Crossfade(
        targetState = state,
        label = "resultScreenAnimation",
    ) { targetState ->
        when {
            targetState.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(50.dp),
                        color = MaterialTheme.colorScheme.primary,
                        strokeWidth = 5.dp,
                    )
                }
            }
        }
    }
}