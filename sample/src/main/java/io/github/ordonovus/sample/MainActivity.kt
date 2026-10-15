package io.github.ordonovus.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import io.github.ordonovus.sample.screen.MainScreen
import io.github.ordonovus.sample.settings.SampleThemeMode
import io.github.ordonovus.theme.UiKitTheme

/**
 * Entry point of the Compose UI Kit sample application.
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            var themeMode by rememberSaveable {
                mutableStateOf(SampleThemeMode.System)
            }

            val darkTheme = themeMode.isDarkTheme(
                systemDarkTheme = isSystemInDarkTheme()
            )

            UiKitTheme(
                darkTheme = darkTheme
            ) {
                MainScreen(
                    selectedThemeMode = themeMode,
                    onThemeModeSelected = { themeMode = it }
                )
            }
        }
    }
}

