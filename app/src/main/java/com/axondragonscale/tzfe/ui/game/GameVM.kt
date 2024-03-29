package com.axondragonscale.tzfe.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axondragonscale.tzfe.data.Direction
import com.axondragonscale.tzfe.data.Matrix
import com.axondragonscale.tzfe.engine.GameEngine
import com.axondragonscale.tzfe.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.abs

/**
 * Created by Ronak Harkhani on 05/06/21
 */
@HiltViewModel
class GameVM @Inject constructor(
    private val gameEngine: GameEngine,
    private val gameRepository: GameRepository
): ViewModel() {

    val uiState = MutableStateFlow(GameUiState(0, 0 , Matrix.emptyMatrix()))

    init {
        viewModelScope.launch {
            val savedGameState = gameRepository.getSavedGameState()
            gameEngine.init(savedGameState)
            updateUiState()
        }
    }

    fun onEvent(event: GameUiEvent) {
        when (event) {
            is GameUiEvent.Push -> push(event.offsetX, event.offsetY)
            GameUiEvent.Reset -> gameEngine.resetBoard()
            GameUiEvent.Undo -> gameEngine.undoMove()
        }
        updateUiState()
        saveGameState()
    }

    private fun push(offsetX: Float, offsetY: Float) {
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
    }

    private fun updateUiState() {
        uiState.update {
            GameUiState(
                score = gameEngine.score,
                highScore = gameEngine.highScore,
                board = gameEngine.board.copy(),
            )
        }
    }

    private fun saveGameState() = viewModelScope.launch {
        gameRepository.saveGameState(gameEngine.gameState)
    }
}
