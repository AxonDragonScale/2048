package com.axondragonscale.tzfe.ui.gamescreen

import androidx.compose.runtime.mutableStateOf
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
    private val gameEngine = GameEngine()

    var board = mutableStateOf(gameEngine.board.copy())

    fun resetBoard() {
        gameEngine.reset()
        updateBoard()
    }

    fun push(offsetX: Float, offsetY: Float) {
        if (abs(offsetX) > abs(offsetY)) {
            when {
                offsetX > 0 -> pushInDirection(Direction.Right)
                offsetX < 0 -> pushInDirection(Direction.Left)
            }
        } else {
            when {
                offsetY > 0 -> pushInDirection(Direction.Down)
                offsetY < 0 -> pushInDirection(Direction.Up)
            }
        }
    }

    private fun pushInDirection(dir: Direction) {
        when (dir) {
            Direction.Left -> gameEngine.pushLeft()
            Direction.Right -> gameEngine.pushRight()
            Direction.Up -> gameEngine.pushUp()
            Direction.Down -> gameEngine.pushDown()
        }

        if (gameEngine.anyMoved) gameEngine.addTile()
        updateBoard()
    }

    private fun updateBoard() {
        board.value = gameEngine.board.copy()
    }
}