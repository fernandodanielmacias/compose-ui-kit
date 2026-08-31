package io.github.ordonovus.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import io.github.ordonovus.sample.component.button.UiButtonSample
import io.github.ordonovus.sample.component.text.UiTypographySample
import io.github.ordonovus.theme.UiKitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UiKitTheme {
                //UiButtonSample()
                UiTypographySample()
            }
        }
    }
}
