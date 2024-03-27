package com.axondragonscale.tzfe.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import com.axondragonscale.tzfe.ui.theme.Colors
import com.axondragonscale.tzfe.ui.theme.FontSizes
import java.lang.RuntimeException
import kotlin.random.Random
import kotlin.random.nextInt

/**
 * Created by Ronak Harkhani on 05/06/21
 */
class Tile constructor(
    val value: Int,
    val id: Int = idCounter++
) {

    companion object {

        private var idCounter = 0

        val EMPTY = Tile(0, -1)

        fun twoOrFour() = if (Random.nextInt(1..10) <= 9) Tile(2) else Tile(4)

        fun canCombine(a: Tile, b: Tile): Boolean {
            if (a.value == 0 || b.value == 0) return false
            return a.value == b.value
        }

        fun combine(a: Tile, b: Tile): Tile {
            if (canCombine(a, b)) return Tile(a.value * 2, b.id)
            else throw RuntimeException("Tiles ${a.value} and ${b.value} cannot be combined")
        }
    }

    val isEmpty: Boolean
        get() = value == 0

    val displayText: String
        get() = if (isEmpty) "" else value.toString()

    val fontColor: Color
        get() = if (value <= 8) Colors.dark else Colors.light

    val fontSize: TextUnit
        get() = FontSizes.TileFontSize[value] ?: FontSizes.SingleDigitTileFontSize

    val tileColor: Color
        get() = Colors.TileColors[value] ?: Colors.TileEmpty

}
