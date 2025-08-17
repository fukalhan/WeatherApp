package cz.fukalhan.ctweatherapp.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cz.fukalhan.ctweatherapp.core.presentation.ComposeViewModel
import cz.fukalhan.ctweatherapp.core.presentation.PreviewViewModel
import cz.fukalhan.ctweatherapp.core.presentation.ScreenContentWrapper
import cz.fukalhan.ctweatherapp.core.presentation.UiState
import cz.fukalhan.ctweatherapp.presentation.model.WeatherForecastVo
import cz.fukalhan.ctweatherapp.presentation.model.WeatherScreenEvent
import cz.fukalhan.ctweatherapp.presentation.model.WeatherScreenVo
import cz.fukalhan.ctweatherapp.ui.ScreenPreview
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    viewModel: ComposeViewModel<WeatherScreenVo, WeatherScreenEvent>
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    LaunchedEffect(viewState.data.showCitiesBottomSheet) {
        if (viewState.data.showCitiesBottomSheet) {
            sheetState.show()
        } else {
            sheetState.hide()
        }
    }

    ScreenContentWrapper(
        state = viewState
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { padding ->

            WeatherScreenContent(
                data = viewState.data,
                sendEvent = viewModel::onEvent,
                modifier = Modifier.padding(padding)
            )

            if (viewState.data.showCitiesBottomSheet) {
                CitiesBottomSheet(
                    sheetState = sheetState,
                    availableCities = viewState.data.availableCities,
                    selectedCity = viewState.data.selectedCity,
                    sendEvent = viewModel::onEvent,
                    onDismiss = {
                        viewModel.onEvent(WeatherScreenEvent.OnDismissCitiesBottomSheet)
                    },
                )
            }
        }
    }
}

@Composable
private fun WeatherScreenContent(
    data: WeatherScreenVo,
    sendEvent: (WeatherScreenEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        if (data.selectedCity != null) {
            DateCarouselPager(
                forecasts = data.forecast,
                selectedIndex = data.getSelectedDateIndex(),
                onDaySelected = sendEvent
            )

            MinMaxTemp(
                minTemp = data.forecast[data.getSelectedDateIndex()].minTemp,
                maxTemp = data.forecast[data.getSelectedDateIndex()].maxTemp,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }

        CityRow(
            selectedCity = data.selectedCity,
            sendEvent = sendEvent,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
    }
}

@Composable
@ScreenPreview
internal fun WeatherScreenPreview() {
    WeatherScreen(
        viewModel = PreviewViewModel(
            state = UiState(
                data = WeatherScreenVo(
                    selectedCity = "Prague",
                    forecast = listOf(
                        WeatherForecastVo(
                            date = LocalDate.now(),
                            minTemp = 10.0,
                            maxTemp = 20.0
                        ),
                        WeatherForecastVo(
                            date = LocalDate.now().plusDays(1),
                            minTemp = 12.0,
                            maxTemp = 22.0
                        ),
                        WeatherForecastVo(
                            date = LocalDate.now().plusDays(2),
                            minTemp = 12.0,
                            maxTemp = 22.0
                        ),
                        WeatherForecastVo(
                            date = LocalDate.now().plusDays(3),
                            minTemp = 12.0,
                            maxTemp = 22.0
                        ),
                        WeatherForecastVo(
                            date = LocalDate.now().plusDays(4),
                            minTemp = 12.0,
                            maxTemp = 22.0
                        ),
                    )
                )
            )
        )
    )
}