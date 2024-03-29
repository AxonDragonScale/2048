package com.axondragonscale.tzfe.ui.board

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import com.axondragonscale.tzfe.data.Matrix
import com.axondragonscale.tzfe.data.Tile
import kotlinx.coroutines.launch

/**
 * Created by Ronak Harkhani on 11/03/23
 */

typealias ItemOffsetAnimatable = Animatable<DpOffset, AnimationVector2D>
typealias ScaleAnimatable = Animatable<Float, AnimationVector1D>

@Composable
fun AnimatedGrid(
    modifier: Modifier = Modifier,
    matrix: Matrix,
    itemContent: @Composable BoxScope.(Tile) -> Unit
) = BoxWithConstraints(modifier) {
    val rows = remember { matrix.rows }
    val cols = remember { matrix.cols }

    val itemSize = remember(rows, cols) {
        DpSize(maxWidth / cols, maxHeight / rows)
    }

    val gridOffsets = remember(rows, cols, itemSize) {
        matrix.mapIndexed { rowIndex, row ->
            row.mapIndexed { colIndex, _ ->
                DpOffset(itemSize.width * colIndex, itemSize.height * rowIndex)
            }
        }
    }

    var oldOffsets by remember { mutableStateOf(mapOf<Int, ItemOffsetAnimatable>()) }
    var oldScales by remember { mutableStateOf(mapOf<Int, ScaleAnimatable>()) }

    val tempOffsets = mutableMapOf<Int, ItemOffsetAnimatable>()
    val tempScales = mutableMapOf<Int, ScaleAnimatable>()
    matrix.iterateIndexed { rowIndex, colIndex, tile ->
        key(tile.id) {
            tempOffsets[tile.id] = oldOffsets[tile.id]
                ?: ItemOffsetAnimatable(gridOffsets[rowIndex][colIndex], DpOffset.VectorConverter)

            tempScales[tile.id] = oldScales[tile.id]
                ?: ScaleAnimatable(0f, Float.VectorConverter)
        }
    }
    oldOffsets = tempOffsets
    oldScales = tempScales

    matrix.iterateIndexed { rowIndex, colIndex, tile ->
        val oldOffset = oldOffsets.getValue(tile.id)
        val oldScale = oldScales.getValue(tile.id)

        Box(
            Modifier
                .size(itemSize)
                .offset(oldOffset.value.x, oldOffset.value.y)
                .scale(oldScale.value)
        ) {
            itemContent(tile)
        }

        LaunchedEffect(tile.id, rowIndex, colIndex) {
            val newOffset = gridOffsets[rowIndex][colIndex]
            oldOffset.animateTo(newOffset, tween(200))
            oldScale.animateTo(1f, tween(200, 50))
        }
    }
}
