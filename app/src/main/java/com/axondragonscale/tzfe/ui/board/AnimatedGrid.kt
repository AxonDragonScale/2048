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
    val itemSize = remember(matrix.rows, matrix.cols) {
        DpSize(maxWidth / matrix.cols, maxHeight / matrix.rows)
    }

    val gridOffsets = remember(matrix.rows, matrix.cols, itemSize) {
        matrix.mapIndexed { rowIndex, row ->
            row.mapIndexed { colIndex, _ ->
                DpOffset(itemSize.width * colIndex, itemSize.height * rowIndex)
            }
        }
    }

    val oldOffsets = remember { mutableMapOf<Int, ItemOffsetAnimatable>() }
    val oldScales = remember { mutableMapOf<Int, ScaleAnimatable>() }
    matrix.iterateIndexed { rowIndex, colIndex, tile ->
        key(tile.id) {
            oldOffsets[tile.id] = oldOffsets[tile.id]
                ?: ItemOffsetAnimatable(gridOffsets[rowIndex][colIndex], DpOffset.VectorConverter)

            oldScales[tile.id] = oldScales[tile.id]
                ?: ScaleAnimatable(0f, Float.VectorConverter)
        }
    }

    matrix.iterateIndexed { rowIndex, colIndex, tile ->
        val oldOffset = oldOffsets.getValue(tile.id)
        val oldScale = oldScales.getValue(tile.id)

        LaunchedEffect(tile.id, rowIndex, colIndex) {
            val newOffset = gridOffsets[rowIndex][colIndex]
            oldOffset.animateTo(newOffset, tween(200))
            oldScale.animateTo(1f, tween(200, 50))
        }

        Box(
            Modifier
                .size(itemSize)
                .offset(oldOffset.value.x, oldOffset.value.y)
                .scale(oldScale.value)
        ) {
            itemContent(tile)
        }
    }
}
