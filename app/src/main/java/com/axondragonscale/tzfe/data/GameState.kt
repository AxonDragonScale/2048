package com.axondragonscale.tzfe.data

import androidx.annotation.Keep
import com.squareup.moshi.JsonClass

/**
 * Created by Ronak Harkhani on 13/03/23
 */
@Keep
@JsonClass(generateAdapter = true)
data class GameState(
    var board: Matrix,
    var prevBoard: Matrix,
    var score: Int,
    var highScore: Int
)
