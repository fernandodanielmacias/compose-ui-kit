package io.github.ordonovus.sample.ui.feedback

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Displays a snackbar message using the sample application's shared host.
 *
 * The currently displayed snackbar is dismissed before showing the new
 * message, preventing previously displayed feedback from remaining visible.
 *
 * If no snackbar host is available, the operation is ignored.
 *
 * @param scope Coroutine scope used to launch the snackbar operation.
 * @param message Message displayed in the snackbar.
 * @param duration Duration of the snackbar.
 */
fun SnackbarHostState?.showSampleMessage(
    scope: CoroutineScope,
    message: String,
    duration: SnackbarDuration = SnackbarDuration.Short
) {
    val hostState = this ?: return

    scope.launch {
        hostState.currentSnackbarData?.dismiss()

        hostState.showSnackbar(
            message = message,
            duration = duration
        )
    }
}