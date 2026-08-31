package io.github.ordonovus.component.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import io.github.ordonovus.theme.UiTypographyBody
import io.github.ordonovus.theme.UiTypographyBodySecondary
import io.github.ordonovus.theme.UiTypographyDisplay
import io.github.ordonovus.theme.UiTypographyHeading
import io.github.ordonovus.theme.UiTypographyLabel
import io.github.ordonovus.theme.UiTypographyTitle

/**
 * Semantic typography variants provided by Compose UI Kit.
 *
 * Each variant maps to a typography role defined by the UI Kit design system.
 * Using semantic variants allows typography to be changed centrally without
 * requiring modifications to individual UI components.
 */
enum class UiTextVariant {

    /**
     * Large display text intended for highly prominent content.
     */
    Display,

    /**
     * Primary heading used for screens or major sections.
     */
    Heading,

    /**
     * Title used for sections, cards, dialogs, and similar content.
     */
    Title,

    /**
     * Primary body text.
     */
    Body,

    /**
     * Secondary body text used for supporting or less prominent content.
     */
    BodySecondary,

    /**
     * Compact text typically used for labels and controls.
     */
    Label
}

/**
 * Returns the typography style associated with this semantic text variant.
 *
 * @return The [TextStyle] defined by the Compose UI Kit typography system.
 */
private fun UiTextVariant.toTextStyle(): TextStyle {
    return when (this) {
        UiTextVariant.Display -> UiTypographyDisplay
        UiTextVariant.Heading -> UiTypographyHeading
        UiTextVariant.Title -> UiTypographyTitle
        UiTextVariant.Body -> UiTypographyBody
        UiTextVariant.BodySecondary -> UiTypographyBodySecondary
        UiTextVariant.Label -> UiTypographyLabel
    }
}

/**
 * Displays text using the semantic typography system provided by Compose UI Kit.
 *
 * [UiText] should be preferred when displaying standard text content so
 * typography remains consistent across an application. The [variant] defines
 * the semantic role of the text while the remaining parameters allow specific
 * properties to be overridden when necessary.
 *
 * By default, the text uses the current content color provided by the active
 * [io.github.ordonovus.theme.UiKitTheme], allowing it to adapt automatically
 * to light and dark themes.
 *
 * @param text Text to display.
 * @param modifier Modifier applied to the text.
 * @param variant Semantic typography variant used by default.
 * @param color Text color. When unspecified, the current content color is used.
 * @param fontSize Optional font size override.
 * @param fontStyle Optional font style override.
 * @param fontWeight Optional font weight override.
 * @param fontFamily Optional font family override.
 * @param letterSpacing Optional letter spacing override.
 * @param textDecoration Optional text decoration.
 * @param textAlign Horizontal alignment of the text.
 * @param lineHeight Optional line height override.
 * @param overflow Behavior when the text exceeds its available space.
 * @param softWrap Whether text should wrap at soft line breaks.
 * @param maxLines Maximum number of lines allowed.
 * @param minLines Minimum number of lines reserved for the text.
 * @param style Optional complete style override. When provided, it replaces
 * the style associated with [variant].
 *
 * Example:
 * ```
 * UiText(
 *     text = "Account information",
 *     variant = UiTextVariant.Title
 * )
 * ```
 *
 * Example with an override:
 * ```
 * UiText(
 *     text = "Required field",
 *     variant = UiTextVariant.BodySecondary,
 *     color = MaterialTheme.colorScheme.error,
 *     fontWeight = FontWeight.Medium
 * )
 * ```
 */
@Composable
fun UiText(
    text: String,
    modifier: Modifier = Modifier,
    variant: UiTextVariant = UiTextVariant.Body,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    style: TextStyle? = null
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        style = style ?: variant.toTextStyle()
    )
}

/**
 * Displays styled or annotated text using the semantic typography system
 * provided by Compose UI Kit.
 *
 * This overload is useful when different sections of the same text require
 * different styles, annotations, or formatting.
 *
 * @param text Annotated text to display.
 * @param modifier Modifier applied to the text.
 * @param variant Semantic typography variant used by default.
 * @param color Text color. When unspecified, the current content color is used.
 * @param textAlign Horizontal alignment of the text.
 * @param overflow Behavior when the text exceeds its available space.
 * @param softWrap Whether text should wrap at soft line breaks.
 * @param maxLines Maximum number of lines allowed.
 * @param minLines Minimum number of lines reserved for the text.
 * @param style Optional complete style override. When provided, it replaces
 * the style associated with [variant].
 *
 * Example:
 * ```
 * UiText(
 *     text = buildAnnotatedString {
 *         append("Status: ")
 *
 *         withStyle(
 *             SpanStyle(fontWeight = FontWeight.Bold)
 *         ) {
 *             append("Approved")
 *         }
 *     },
 *     variant = UiTextVariant.Body
 * )
 * ```
 */
@Composable
fun UiText(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    variant: UiTextVariant = UiTextVariant.Body,
    color: Color = Color.Unspecified,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    style: TextStyle? = null
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        style = style ?: variant.toTextStyle()
    )
}
