package cz.fukalhan.ctweatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cz.fukalhan.ctweatherapp.core.presentation.ComposeViewModel
import cz.fukalhan.ctweatherapp.presentation.model.WeatherScreenVo
import cz.fukalhan.ctweatherapp.presentation.view.WeatherScreen
import cz.fukalhan.ctweatherapp.presentation.viewmodel.WeatherScreenViewModel
import cz.fukalhan.ctweatherapp.ui.theme.CtWeatherAppTheme
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.compose.viewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CtWeatherAppTheme {
                WeatherScreen(
                    viewModel = getViewModel<WeatherScreenViewModel>()
                )
            }
        }
    }
}