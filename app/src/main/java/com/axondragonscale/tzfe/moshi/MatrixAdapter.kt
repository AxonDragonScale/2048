package com.axondragonscale.tzfe.moshi

import com.axondragonscale.tzfe.data.Matrix
import com.axondragonscale.tzfe.data.Tile
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

/**
 * Created by Ronak Harkhani on 17/03/23
 */
class MatrixAdapter {

    @FromJson
    fun fromJson(base: MutableList<MutableList<Tile>>): Matrix = Matrix(base)

    @ToJson
    fun toJson(matrix: Matrix): MutableList<MutableList<Tile>> = matrix.base
}
