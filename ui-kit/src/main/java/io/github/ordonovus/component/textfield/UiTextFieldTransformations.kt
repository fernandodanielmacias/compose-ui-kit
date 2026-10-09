package io.github.ordonovus.component.textfield

import androidx.compose.foundation.text.input.InputTransformation

/**
 * Provides reusable input transformations for text field components.
 *
 * These transformations define common input restrictions without imposing
 * application-specific business rules.
 */
object UiTextFieldTransformations {

    /**
     * Creates an input transformation that accepts only decimal digits.
     *
     * Input changes containing characters other than `0` through `9` are
     * rejected, preserving the previous valid text.
     *
     * This transformation can be combined with the `maxLength` parameter
     * provided by [UiTextField] and [UiOutlinedTextField].
     *
     * Example:
     * ```
     * UiTextField(
     *     state = state,
     *     maxLength = 3,
     *     inputTransformation = UiTextFieldTransformations.digitsOnly()
     * )
     * ```
     *
     * @return An [InputTransformation] that accepts only decimal digits.
     */
    fun digitsOnly(): InputTransformation {
        return InputTransformation {
            if (!asCharSequence().all(Char::isDigit)) {
                revertAllChanges()
            }
        }
    }
}