package com.hfad.cookbook.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.hfad.cookbook.R

private val Roboto = FontFamily(
    Font(R.font.roboto_regular),
    Font(R.font.roboto_bold, FontWeight.Bold)
)

val Courgette = FontFamily(
    Font(R.font.courgette_regular)
)

val defaultTypography = Typography()

val CookbookTypography = Typography(
    h1 = defaultTypography.h1.copy(fontFamily = Roboto),
    h2 = defaultTypography.h2.copy(fontFamily = Roboto),
    h3 = defaultTypography.h3.copy(fontFamily = Roboto),
    h4 = defaultTypography.h4.copy(fontFamily = Roboto),
    h5 = defaultTypography.h5.copy(fontFamily = Roboto),
    h6 = defaultTypography.h6.copy(fontFamily = Roboto),
    subtitle1 = defaultTypography.subtitle1.copy(fontFamily = Roboto),
    subtitle2 = defaultTypography.subtitle2.copy(fontFamily = Roboto),
    body1 = defaultTypography.body1.copy(fontFamily = Roboto),
    body2 = defaultTypography.body2.copy(fontFamily = Roboto),
    button = defaultTypography.button.copy(fontFamily = Roboto),
    caption = defaultTypography.caption.copy(fontFamily = Roboto),
    overline = defaultTypography.overline.copy(fontFamily = Roboto)
)