package com.axondragonscale.tzfe.engine

import com.axondragonscale.tzfe.data.Matrix
import com.axondragonscale.tzfe.data.Tile

/**
 * Created by Ronak Harkhani on 05/06/21
 */
class GameEngine {
    var board = Matrix.emptyMatrix()
    var anyMoved = false

    init {
        reset()
    }

    fun reset() {
        board = Matrix.emptyMatrix()
        repeat(2) { addTile() }
    }

    fun addTile() {
        val (row, col) = getEmptyPositions().random()
        board.base[row][col] = Tile.twoOrFour()
    }

    private fun getEmptyPositions(): List<Pair<Int, Int>> {
        return board.base.flatMapIndexed { rowIndex, row ->
            row.mapIndexedNotNull { colIndex, tile ->
                if (tile.isEmpty) Pair(rowIndex, colIndex) else null
            }
        }
    }

    fun pushLeft() {
        anyMoved = false
        slide()
        combine()
        slide()
    }

    fun pushRight() {
        board.flipHorizontally()
        pushLeft()
        board.flipHorizontally()
    }

    fun pushUp() {
        board.transpose()
        pushLeft()
        board.transpose()
    }

    fun pushDown() {
        board.transpose()
        board.flipHorizontally()
        pushLeft()
        board.flipHorizontally()
        board.transpose()
    }

    private fun slide() {
        board.base.forEach { row ->
            val newRow = row.filter { !it.isEmpty } + row.filter { it.isEmpty }
            newRow.zip(row).forEach {
                if (it.first.value != it.second.value) anyMoved = true
            }
            for (col in 0 until row.size) {
                row[col] = newRow[col]
            }
        }
    }

    private fun combine() {
        board.base.forEach { row ->
            for (col in 0 until row.size - 1) {
                if (Tile.canCombine(row[col], row[col + 1])) {
                    row[col] = row[col] + row[col + 1]
                    row[col + 1] = Tile.empty
                }
            }
        }
    }
}