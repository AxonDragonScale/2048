package com.axondragonscale.tzfe.data

/**
 * Created by Ronak Harkhani on 05/06/21
 */
class Matrix private constructor(
    base: MutableList<MutableList<Tile>>
) : MutableList<MutableList<Tile>> by base {

    companion object {
        fun emptyMatrix(size: Int = 4): Matrix {
            return Matrix(MutableList(size) { MutableList(size) { Tile.EMPTY } })
        }
    }

    val rows: Int
        get() = size

    val cols: Int
        get() = first().size

    fun flipHorizontally() {
        this.forEach {
            it.reverse()
        }
    }

    fun transpose() {
        for (row in 0 until size) {
            for (col in row+1 until size) {
                this[row][col] = this[col][row].also {
                    this[col][row] = this[row][col]
                }
            }
        }
    }

    inline fun iterateIndexed(block: (rowIndex: Int, colIndex: Int, tile: Tile) -> Unit) {
        this.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { colIndex, tile ->
                block(rowIndex, colIndex, tile)
            }
        }
    }

    fun copy(): Matrix {
        return Matrix(
            MutableList(size) { row ->
                MutableList(size) { col ->
                    this[row][col]
                }
            }
        )
    }

    fun print() {
        this.forEach { row ->
            row.forEach { tile ->
                print("${tile.value} ")
            }
        }
    }
}
