package io.github.ordonovus.component.textfield

import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.insert
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.then

/**
 * Provides reusable input and output transformations for text field components.
 *
 * These transformations implement common input restrictions and display
 * formatting without introducing application-specific business rules.
 *
 * Input transformations can modify or reject user input, while output
 * transformations change only the displayed representation of the text.
 *
 * The transformations can be used with [UiTextField] and
 * [UiOutlinedTextField].
 */
object UiTextFieldTransformations {

    /**
     * Creates an input transformation that accepts only ASCII decimal digits.
     *
     * Input changes containing characters outside the range `0` through `9`
     * are rejected, preserving the previously accepted text.
     *
     * This transformation can be combined with the `maxLength` parameter
     * provided by [UiTextField] and [UiOutlinedTextField].
     *
     * Example:
     * ```
     * UiTextField(
     *     state = state,
     *     maxLength = 8,
     *     inputTransformation = UiTextFieldTransformations.digitsOnly()
     * )
     * ```
     *
     * @return An [InputTransformation] that accepts only ASCII decimal digits.
     */
    fun digitsOnly(): InputTransformation {
        return InputTransformation {
            if (!asCharSequence().all { it in '0'..'9' }) {
                revertAllChanges()
            }
        }
    }

    /**
     * Creates an output transformation that groups displayed characters
     * using a configurable group size and separator.
     *
     * Separators are inserted into the displayed text only. The underlying
     * TextFieldState remains unchanged.
     *
     * This transformation does not validate the input characters. It can
     * be combined with [digitsOnly] when numeric input is required.
     *
     * Example:
     * ```
     * UiOutlinedTextField(
     *     state = state,
     *     inputTransformation = UiTextFieldTransformations.digitsOnly(),
     *     outputTransformation = UiTextFieldTransformations.groupedCharacters(),
     *     maxLength = 8
     * )
     * ```
     *
     * @param groupSize Number of characters in each group. Must be positive.
     * @param separator Text inserted between consecutive groups.
     * Must not be empty.
     * @return An [OutputTransformation] that groups displayed characters.
     *
     * @throws IllegalArgumentException If [groupSize] is not positive
     * or [separator] is empty.
     */
    fun groupedCharacters(
        groupSize: Int = 4,
        separator: String = " "
    ): OutputTransformation {
        require(groupSize > 0) {
            "groupSize must be greater than zero."
        }
        require(separator.isNotEmpty()) {
            "separator must not be empty."
        }

        return OutputTransformation {
            var index = groupSize

            while (index < length) {
                insert(index, separator)
                index += groupSize + separator.length
            }
        }
    }

    /**
     * Combines a maximum-length restriction with an optional input transformation.
     *
     * The maximum-length restriction is applied before the provided transformation.
     * If no restrictions are specified, this function returns `null`.
     *
     * @param maxLength Maximum number of accepted characters, or `null`
     * when no length restriction is required.
     * @param inputTransformation Optional additional input transformation.
     * @return The combined transformation, or `null` when neither is provided.
     * @throws IllegalArgumentException If [maxLength] is negative.
     */
    internal fun combine(
        maxLength: Int?,
        inputTransformation: InputTransformation?
    ): InputTransformation? {
        require(maxLength == null || maxLength >= 0) {
            "maxLength must be greater than or equal to zero."
        }

        val lengthTransformation = maxLength?.let {
            InputTransformation.maxLength(it)
        }

        return when {
            lengthTransformation != null && inputTransformation != null ->
                lengthTransformation.then(inputTransformation)

            lengthTransformation != null ->
                lengthTransformation

            else ->
                inputTransformation
        }
    }

}