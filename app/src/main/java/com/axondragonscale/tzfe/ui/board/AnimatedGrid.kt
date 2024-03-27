package com.axondragonscale.tzfe.ui.board

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.spring
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
import com.axondragonscale.tzfe.data.Tile
import kotlinx.coroutines.launch

/**
 * Created by Ronak Harkhani on 11/03/23
 */

typealias ItemOffsetAnimatable = Animatable<DpOffset, AnimationVector2D>
typealias ScaleAnimatable = Animatable<Float, AnimationVector1D>
typealias GridAnimtable = Pair<ItemOffsetAnimatable, ScaleAnimatable>

@Composable
fun AnimatedGrid(
    modifier: Modifier = Modifier,
    items: List<List<Tile>>,
    itemContent: @Composable BoxScope.(Tile) -> Unit
) = BoxWithConstraints(modifier) {
    val rows = remember { items.size }
    val cols = remember { items.first().size }

    val itemSize = remember(rows, cols) {
        DpSize(maxWidth / cols, maxHeight / rows)
    }

    val gridOffsets = remember(rows, cols, itemSize) {
        items.mapIndexed { rowIndex, row ->
            row.mapIndexed { colIndex, _ ->
                DpOffset(itemSize.width * colIndex, itemSize.height * rowIndex)
            }
        }
    }

    var oldOffsets by remember { mutableStateOf(mapOf<Int, ItemOffsetAnimatable>()) }
    var oldScales by remember { mutableStateOf(mapOf<Int, ScaleAnimatable>()) }

    val tempOffsets = mutableMapOf<Int, ItemOffsetAnimatable>()
    val tempScales = mutableMapOf<Int, ScaleAnimatable>()
    items.mapIndexed { rowIndex, row ->
        row.mapIndexed { colIndex, item ->
            key(item.id) {
                tempOffsets[item.id] = oldOffsets[item.id]
                    ?: ItemOffsetAnimatable(gridOffsets[rowIndex][colIndex], DpOffset.VectorConverter)
                tempScales[item.id] = oldScales[item.id] ?: ScaleAnimatable(0f, Float.VectorConverter)
            }
        }
    }
    oldOffsets = tempOffsets
    oldScales = tempScales

    items.forEach { row ->
        row.forEach { item ->
            val offset = oldOffsets.getValue(item.id).value
            val oldScale = oldScales.getValue(item.id).value
            Box(
                Modifier
                    .size(itemSize)
                    .offset(offset.x, offset.y)
                    .scale(oldScale)
            ) {
                itemContent(item)
            }
        }
    }

    items.forEachIndexed { rowIndex, row ->
        row.forEachIndexed { colIndex, item ->
//            if (item.isEmpty) return@forEachIndexed

            val oldOffset = oldOffsets.getValue(item.id)
            val oldScale = oldScales.getValue(item.id)

            LaunchedEffect(item.id) {
                val newOffset = gridOffsets[rowIndex][colIndex]
                launch {
                    oldOffset.animateTo(newOffset, tween(200))
                    oldScale.animateTo(1f, tween(200, 50))
                }
            }
        }
    }
}
