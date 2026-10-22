package io.github.ordonovus.sample.ui.feedback

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.compositionLocalOf

/**
 * Provides access to the sample application's shared snackbar host.
 *
 * The host is owned by the screen Scaffold and can be used by
 * contextual component demonstrations.
 */
val LocalSampleSnackbarHostState = compositionLocalOf<SnackbarHostState?> {
    null
}
