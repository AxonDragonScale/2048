package com.axondragonscale.tzfe.data

import androidx.compose.ui.graphics.Color
import com.axondragonscale.tzfe.ui.theme.Colors
import java.lang.RuntimeException
import kotlin.random.Random
import kotlin.random.nextInt

/**
 * Created by Ronak Harkhani on 05/06/21
 */
class Tile private constructor(
    val value: Int
) {

    companion object {
        val empty = Tile(0)
        val two = Tile(2)
        val four = Tile(4)

        fun empty() = empty
        fun two() = two
        fun four() = four

        fun twoOrFour() = if (Random.nextInt(1..10) <= 9) two else four

        fun canCombine(a: Tile, b: Tile): Boolean {
            if (a.value == 0 || b.value == 0) return false
            return a.value == b.value
        }

        fun combine(a: Tile, b: Tile): Tile {
            if (canCombine(a, b)) return Tile(a.value * 2)
            else throw RuntimeException("Tiles ${a.value} and ${b.value} cannot be combined")
        }
    }

    val isEmpty: Boolean
        get() = value == 0

    val displayText: String
        get() = if (isEmpty) "" else value.toString()

    val fontColor: Color
        get() = if (value <= 8) Colors.dark else Colors.light

    val tileColor: Color
        get() = Colors.TileColors[value] ?: Colors.TileEmpty

    operator fun plus(t: Tile): Tile {
        return combine(this, t)
    }

    fun copy() = Tile(value)
}