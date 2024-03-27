package com.axondragonscale.tzfe.data

/**
 * Created by Ronak Harkhani on 05/06/21
 */
class Matrix private constructor(val base: MutableList<MutableList<Tile>>) {

    // TODO: Matrix should not have a list of list, it should be a list of list

    companion object {
        var size = 4
        private val rows = 0 until size
        private val cols = rows

        fun emptyMatrix(): Matrix {
            return Matrix(MutableList(size) { MutableList(size) { Tile.empty() } })
        }
    }

    fun get(row: Int, col: Int): Tile {
        return base[row][col]
    }

    fun transpose() {
        for (row in 0 until size) {
            for (col in row+1 until size) {
                base[row][col] = base[col][row].also {
                    base[col][row] = base[row][col]
                }
            }
        }
    }

    fun flipHorizontally() {
        base.forEach {
            it.reverse()
        }
    }

    fun copy(): Matrix {
        return Matrix(
            MutableList(size) { row ->
                MutableList(size) { col ->
                    base[row][col]
                }
            }
        )
    }

    fun print() {
        base.forEach { row ->
            row.forEach { tile ->
                print("${tile.value} ")
            }
        }
    }
}
