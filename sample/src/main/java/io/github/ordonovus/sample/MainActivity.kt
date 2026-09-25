package io.github.ordonovus.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import io.github.ordonovus.sample.screen.MainScreen
import io.github.ordonovus.theme.UiKitTheme

/**
 * Entry point of the Compose UI Kit sample application.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UiKitTheme {
                MainScreen()
            }
        }
    }
}
