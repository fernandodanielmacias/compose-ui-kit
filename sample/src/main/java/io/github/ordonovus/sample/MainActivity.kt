package io.github.ordonovus.sample

import android.graphics.Color as AndroidColor
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.lifecycle.lifecycleScope
import io.github.ordonovus.sample.data.repository.SamplePreferencesRepository
import io.github.ordonovus.sample.screen.MainScreen
import io.github.ordonovus.sample.settings.SampleThemeMode
import io.github.ordonovus.theme.UiKitTheme
import kotlinx.coroutines.launch

/**
 * Entry point of the Compose UI Kit sample application.
 *
 * Restores the persisted appearance preference, applies the selected
 * theme, and synchronizes the system bars with the application theme.
 */
class MainActivity : ComponentActivity() {

    private val preferencesRepository by lazy {
        SamplePreferencesRepository(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            val themeMode by produceState<SampleThemeMode?>(
                initialValue = null,
                key1 = preferencesRepository
            ) {
                preferencesRepository.themeMode.collect { mode ->
                    value = mode
                }
            }

            themeMode?.let { selectedThemeMode ->
                val darkTheme = selectedThemeMode.isDarkTheme(
                    systemDarkTheme = isSystemInDarkTheme()
                )

                val systemBarStyle = if (darkTheme) {
                    SystemBarStyle.dark(
                        AndroidColor.TRANSPARENT
                    )
                } else {
                    SystemBarStyle.light(
                        AndroidColor.TRANSPARENT,
                        AndroidColor.TRANSPARENT
                    )
                }

                SideEffect {
                    enableEdgeToEdge(
                        statusBarStyle = systemBarStyle,
                        navigationBarStyle = systemBarStyle
                    )
                }

                UiKitTheme(
                    darkTheme = darkTheme
                ) {
                    MainScreen(
                        selectedThemeMode = selectedThemeMode,
                        onThemeModeSelected = { mode ->
                            lifecycleScope.launch {
                                preferencesRepository.setThemeMode(mode)
                            }
                        }
                    )
                }
            }
        }
    }
}