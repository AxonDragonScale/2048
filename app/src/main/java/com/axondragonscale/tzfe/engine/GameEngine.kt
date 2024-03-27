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

    var score = 0
    var highScore = 0

    private var moveScore = 0
    private var anyMoved = false
    private var anyCombined = false

    init {
        resetBoard()
    }

    fun resetBoard() {
        board = Matrix.emptyMatrix()
        repeat(2) { addTile() }
        prevBoard = board.copy()
        score = 0
    }

    fun undoMove() {
        board = prevBoard.copy()
        score -= moveScore
        moveScore = 0
    }

    fun push(dir: Direction) {
        initNewMove()
        val tempBoard = board.copy()
        when (dir) {
            Direction.Left -> pushLeft()
            Direction.Right -> pushRight()
            Direction.Up -> pushUp()
            Direction.Down -> pushDown()
        }

        updateScoreBy(moveScore)
        if (anyMoved || anyCombined) {
            addTile()
            prevBoard = tempBoard
        }
    }

    private fun updateScoreBy(points: Int) {
        score += points
        if (score > highScore) {
            highScore = score
        }
    }

    private fun addTile() {
        val (row, col) = getEmptyPositions().random()
        board[row][col] = Tile.twoOrFour()
    }

    private fun getEmptyPositions(): List<Pair<Int, Int>> {
        return board.flatMapIndexed { rowIndex, row ->
            row.mapIndexedNotNull { colIndex, tile ->
                if (tile.isEmpty) Pair(rowIndex, colIndex) else null
            }
        }
    }

    private fun initNewMove() {
        anyMoved = false
        anyCombined = false
        moveScore = 0
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
        board.forEach { row ->
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
        for (row in board) {
            for (col in 0 until row.size - 1) {
                if (Tile.canCombine(row[col], row[col + 1])) {
                    row[col] = Tile.combine(row[col], row[col + 1])
                    row[col + 1] = Tile.EMPTY
                    moveScore += row[col].value
                    anyCombined = true
                }
            }
        }
    }
}
