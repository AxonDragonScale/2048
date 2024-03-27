package com.axondragonscale.tzfe.engine

import com.axondragonscale.tzfe.data.Direction
import com.axondragonscale.tzfe.data.Matrix
import com.axondragonscale.tzfe.data.Tile

/**
 * Created by Ronak Harkhani on 05/06/21
 */
class GameEngine {
    private var prevBoard = Matrix.emptyMatrix()
    var board = Matrix.emptyMatrix()

    private var anyMoved = false
    private var anyCombined = false

    init {
        resetBoard()
    }

    fun resetBoard() {
        board = Matrix.emptyMatrix()
        repeat(2) { addTile() }
        prevBoard = board.copy()
    }

    fun undoMove() {
        board = prevBoard.copy()
    }

    fun push(dir: Direction) {
        initNewMove()
        when (dir) {
            Direction.Left -> pushLeft()
            Direction.Right -> pushRight()
            Direction.Up -> pushUp()
            Direction.Down -> pushDown()
        }
    }

    fun addTileIfPushed() {
        if (anyMoved || anyCombined) addTile()
    }

    private fun addTile() {
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

    private fun initNewMove() {
        prevBoard = board.copy()
        anyMoved = false
        anyCombined = false
    }

    private fun pushLeft() {
        slide()
        combine()
        slide()
    }

    private fun pushRight() {
        board.flipHorizontally()
        pushLeft()
        board.flipHorizontally()
    }

    private fun pushUp() {
        board.transpose()
        pushLeft()
        board.transpose()
    }

    private fun pushDown() {
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