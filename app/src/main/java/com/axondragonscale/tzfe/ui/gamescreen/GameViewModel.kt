package com.axondragonscale.tzfe.ui.gamescreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.axondragonscale.tzfe.data.Direction
import com.axondragonscale.tzfe.engine.GameEngine
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Ronak Harkhani on 05/06/21
 */
@HiltViewModel
class GameViewModel @Inject constructor(): ViewModel() {
    val gameEngine = GameEngine()

    var board = mutableStateOf(gameEngine.board.copy())

    fun pushInDirection(dir: Direction) {
        when (dir) {
            Direction.Left -> gameEngine.pushLeft()
            Direction.Right -> gameEngine.pushRight()
            Direction.Up -> gameEngine.pushUp()
            Direction.Down -> gameEngine.pushDown()
        }

        gameEngine.addTile()
        updateBoard()
    }

    private fun updateBoard() {
        board.value = gameEngine.board.copy()
    }
}