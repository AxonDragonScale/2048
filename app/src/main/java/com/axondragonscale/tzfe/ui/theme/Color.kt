package com.axondragonscale.tzfe.ui.theme

import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

object Colors {
    val GameBackground = Color(251, 248, 240)
    val BoardBackground = Color(183, 173, 162)

    // Tile Colors
    val TileEmpty = Color(197, 186, 175)
    val Tile2 = Color(235, 228, 220)
    val Tile4 = Color(233, 179, 204)
    val Tile8 = Color(225, 179, 135)
    val Tile16 = Color(215, 151, 112)
    val Tile32 = Color(211, 131, 105)
    val Tile64 = Color(214, 113, 85)

    val TileColors = mapOf(
        0 to TileEmpty,
        2 to Tile2,
        4 to Tile4,
        8 to Tile8,
        16 to Tile16,
        32 to Tile32,
        64 to Tile64,
        128 to Tile64,
        256 to Tile64,
        512 to Tile64,
        1024 to Tile64,
        2048 to Tile64,
        4096 to Tile64,
        8192 to Tile64,
        16384 to Tile64,
    )

    // Tile Fonts
    val light = Color.White
    val dark = Color(116, 110, 103)
}
