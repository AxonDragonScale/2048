package com.axondragonscale.tzfe.ui.theme

import androidx.compose.ui.graphics.Color

object Colors {
    val GameBackground = Color(251, 248, 240)
    val BoardBackground = Color(183, 173, 162)

    // Tile Colors
    val TileEmpty = Color(197, 186, 175)
    val Tile2 = Color(0xffeee4da)
    val Tile4 = Color(0xffede0c8)
    val Tile8 = Color(0xfff2b179)
    val Tile16 = Color(0xfff59563)
    val Tile32 = Color(0xfff67c5f)
    val Tile64 = Color(0xfff65e3b)
    val Tile128 = Color(0xffedcf72)
    val Tile256 = Color(0xffedcc61)
    val Tile512 = Color(0xffedc850)
    val Tile1024 = Color(0xffedc53f)
    val Tile2048 = Color(0xffedc22e)

    val TileColors = mapOf(
        0 to TileEmpty,
        2 to Tile2,
        4 to Tile4,
        8 to Tile8,
        16 to Tile16,
        32 to Tile32,
        64 to Tile64,
        128 to Tile128,
        256 to Tile256,
        512 to Tile512,
        1024 to Tile1024,
        2048 to Tile2048,
        4096 to Tile64,
        8192 to Tile64,
        16384 to Tile64,
    )

    // Tile Fonts
    val light = Color.White
    val dark = Color(116, 110, 103)
}
