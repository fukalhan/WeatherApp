package cz.fukalhan.ctweatherapp.presentation.view

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cz.fukalhan.ctweatherapp.presentation.model.WeatherForecastVo
import cz.fukalhan.ctweatherapp.presentation.model.WeatherScreenEvent

/**
 * Composable function that displays a horizontal carousel of dates for weather forecasts.
 *
 * @param forecasts List of weather forecasts to display in the carousel.
 * @param selectedIndex Index of the currently selected date in the carousel.
 * @param onDaySelected Callback invoked when a date is selected, passing the selected date event.
 */
@Composable
internal fun DateCarouselPager(
    forecasts: List<WeatherForecastVo>,
    selectedIndex: Int,
    onDaySelected: (WeatherScreenEvent) -> Unit
) {
    val listState = rememberLazyListState()

    LaunchedEffect(selectedIndex) {
        listState.animateScrollToItem(selectedIndex, scrollOffset = 0)
    }

    LazyRow(
        state = listState,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {
        itemsIndexed(forecasts) { index, forecast ->
            val isSelected = index == selectedIndex
            val scale by animateFloatAsState(targetValue = if (isSelected) 1.2f else 1f)

            ElevatedCard(
                onClick = {
                    onDaySelected(
                        WeatherScreenEvent.OnSelectDate(date = forecast.date)
                    )
                },
                modifier = Modifier
                    .width(72.dp)
                    .padding(
                        horizontal = if (isSelected) 4.dp else 0.dp,
                    )
                    .graphicsLayer { scaleX = scale; scaleY = scale },
                colors = CardDefaults.elevatedCardColors(
                    containerColor = if (isSelected) {
                        MaterialTheme.colorScheme.surface
                    } else {
                        MaterialTheme.colorScheme.surface
                    }
                ),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = if (isSelected) 8.dp else 4.dp,
                    pressedElevation = if (isSelected) 12.dp else 6.dp,
                    hoveredElevation = if (isSelected) 10.dp else 5.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp, horizontal = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = forecast.date.dayOfWeek.name.take(3),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = forecast.date.dayOfMonth.toString(),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = if (isSelected) {
                            FontWeight.Bold
                        } else {
                            FontWeight.Normal
                        },
                        color = if (isSelected) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.onSurface
                        }
                    )
                }
            }
        }
    }
}