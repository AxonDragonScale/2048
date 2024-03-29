package com.axondragonscale.tzfe.ui.game

/**
 * Created by Ronak Harkhani on 29/03/24
 */
sealed interface GameUiEvent {

    data object Reset : GameUiEvent

    data object Undo: GameUiEvent

    data class Push(val offsetX: Float, val offsetY: Float): GameUiEvent

}
