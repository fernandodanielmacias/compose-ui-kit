package io.github.ordonovus.ui.window

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.window.DialogWindowProvider

/**
 * Applies immersive mode to the current Compose dialog window.
 *
 * This effect must be called from content hosted inside a Dialog.
 * It has no effect when a dialog window is unavailable.
 */
@Composable
fun UiDialogImmersiveEffect() {
    val view = LocalView.current
    val window = (view.parent as? DialogWindowProvider)?.window

    DisposableEffect(window) {
        if (window != null) {
            enableUiImmersiveMode(window)
        }

        onDispose {
            if (window != null) {
                disableUiImmersiveMode(window)
            }
        }
    }
}