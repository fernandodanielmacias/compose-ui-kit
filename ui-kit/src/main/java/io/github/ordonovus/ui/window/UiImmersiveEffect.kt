package io.github.ordonovus.ui.window

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext

/**
 * Applies immersive mode while the calling composable remains
 * in the composition.
 *
 * System bars are restored when the effect leaves the composition.
 *
 * This effect controls the host activity window. It is intended
 * for full-screen content rather than separate dialog windows.
 *
 * @param enabled Whether immersive mode should be active.
 */
@Composable
fun UiImmersiveEffect(
    enabled: Boolean = true
) {
    val context = LocalContext.current

    val activity = generateSequence(context as Context?) {
        (it as? ContextWrapper)?.baseContext
    }.filterIsInstance<Activity>().firstOrNull()

    DisposableEffect(activity, enabled) {
        val window = activity?.window

        if (enabled && window != null) {
            enableUiImmersiveMode(window)
        }

        onDispose {
            if (enabled && window != null) {
                disableUiImmersiveMode(window)
            }
        }
    }
}