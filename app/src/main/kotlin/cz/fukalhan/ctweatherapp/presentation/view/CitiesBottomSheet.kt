package cz.fukalhan.ctweatherapp.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cz.fukalhan.ctweatherapp.presentation.model.WeatherScreenEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CitiesBottomSheet(
    availableCities: List<String>,
    sheetState: SheetState,
    selectedCity: String?,
    sendEvent: (WeatherScreenEvent) -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Select City",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            LazyColumn {
                items(availableCities) { city ->
                    val isSelected = city == selectedCity
                    Text(
                        text = city,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        ),
                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                sendEvent(WeatherScreenEvent.OnCitySelected(city))
                                onDismiss()
                            }
                            .padding(vertical = 12.dp, horizontal = 8.dp)
                    )
                }
            }
        }
    }
}