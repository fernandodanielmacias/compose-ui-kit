package io.github.ordonovus.ui.window

import android.view.Window
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

/**
 * Enables immersive mode for the specified Android window.
 *
 * Hides the status and navigation bars while allowing them to
 * appear temporarily through system gestures.
 *
 * @param window Window whose system bars will be hidden.
 */
fun enableUiImmersiveMode(window: Window) {
    WindowCompat.getInsetsController(
        window,
        window.decorView
    ).apply {
        systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        hide(WindowInsetsCompat.Type.systemBars())
    }
}

/**
 * Requests the status and navigation bars to become visible.
 *
 * @param window Window whose system bars will be shown.
 */
fun disableUiImmersiveMode(window: Window) {
    WindowCompat.getInsetsController(
        window,
        window.decorView
    ).show(WindowInsetsCompat.Type.systemBars())
}