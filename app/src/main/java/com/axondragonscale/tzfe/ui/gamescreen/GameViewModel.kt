package com.axondragonscale.tzfe.ui.gamescreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.axondragonscale.tzfe.data.Direction
import com.axondragonscale.tzfe.engine.GameEngine
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.abs

/**
 * Created by Ronak Harkhani on 05/06/21
 */
@HiltViewModel
class GameViewModel @Inject constructor(): ViewModel() {
    val gameEngine = GameEngine()

    var board by mutableStateOf(gameEngine.board.copy())
    var score by mutableStateOf(gameEngine.score)
    var highScore by mutableStateOf(gameEngine.highScore)

    fun resetBoard() {
        gameEngine.resetBoard()
        update()
    }

    fun undoMove() {
        gameEngine.undoMove()
        update()
    }

    fun push(offsetX: Float, offsetY: Float) {
        if (abs(offsetX) > abs(offsetY)) {
            when {
                offsetX > 0 -> gameEngine.push(Direction.Right)
                offsetX < 0 -> gameEngine.push(Direction.Left)
            }
        } else {
            when {
                offsetY > 0 -> gameEngine.push(Direction.Down)
                offsetY < 0 -> gameEngine.push(Direction.Up)
            }
        }

        update()
    }

    private fun update() {
        board = gameEngine.board.copy()
        score = gameEngine.score
        highScore = gameEngine.highScore
    }
}