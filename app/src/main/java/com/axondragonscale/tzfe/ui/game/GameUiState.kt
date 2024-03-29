package com.axondragonscale.tzfe.ui.game

import com.axondragonscale.tzfe.data.Matrix

/**
 * Created by Ronak Harkhani on 29/03/24
 */
data class GameUiState(
    val score: Int,
    val highScore: Int,
    val board: Matrix,
)
