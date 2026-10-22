package io.github.ordonovus.sample.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import io.github.ordonovus.sample.R
import io.github.ordonovus.sample.ui.layout.SampleBrandLogoSize
import io.github.ordonovus.sample.ui.layout.SampleHeaderHeight
import io.github.ordonovus.theme.UiDimensMedium
import io.github.ordonovus.theme.UiDimensSmall

/**
 * Displays the sample application's brand identity.
 *
 * Combines the application logo, styled brand name,
 * and localized description.
 */
@Composable
internal fun SampleBrandHeader() {
    val colorScheme = MaterialTheme.colorScheme

    val brandTitle = buildAnnotatedString {
        withStyle(
            SpanStyle(color = colorScheme.onSurface)
        ) {
            append("Compose ")
        }

        withStyle(
            SpanStyle(color = colorScheme.primary)
        ) {
            append("UI Kit")
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(SampleHeaderHeight)
            .background(colorScheme.surfaceContainerLow)
            .padding(horizontal = UiDimensMedium),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(UiDimensSmall)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_compose_ui_kit_logo),
            contentDescription = null,
            modifier = Modifier.size(SampleBrandLogoSize)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(UiDimensSmall)
        ) {
            Text(
                text = brandTitle,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontSize = 28.sp,
                    lineHeight = 32.sp,
                    fontWeight = FontWeight.Bold
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = stringResource(R.string.sample_catalog_description),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 14.sp
                ),
                color = colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}