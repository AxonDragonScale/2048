package com.axondragonscale.tzfe.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

object FontSizes {

    val SingleDigitTileFontSize = 40.sp
    val DoubleDigitTileFontSize = 36.sp
    val TripleDigitTileFontSize = 32.sp
    val QuadrupleDigitTileFontSize = 28.sp
    val QuintupleDigitTileFontSize = 24.sp

    val TileFontSize = mapOf(
        0 to SingleDigitTileFontSize,
        2 to SingleDigitTileFontSize,
        4 to SingleDigitTileFontSize,
        8 to SingleDigitTileFontSize,
        16 to DoubleDigitTileFontSize,
        32 to DoubleDigitTileFontSize,
        64 to DoubleDigitTileFontSize,
        128 to TripleDigitTileFontSize,
        256 to TripleDigitTileFontSize,
        512 to TripleDigitTileFontSize,
        1024 to QuadrupleDigitTileFontSize,
        2048 to QuadrupleDigitTileFontSize,
        4096 to QuadrupleDigitTileFontSize,
        8192 to QuadrupleDigitTileFontSize,
        16384 to QuintupleDigitTileFontSize,
    )
}
