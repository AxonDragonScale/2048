package com.axondragonscale.tzfe.ui.board

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize

/**
 * Created by Ronak Harkhani on 11/03/23
 */

@Composable
fun EmptyGrid(
    modifier: Modifier = Modifier,
    rows: Int,
    cols: Int,
    itemContent: @Composable BoxScope.() -> Unit
) = BoxWithConstraints(modifier) {
    val itemSize = remember(rows, cols) {
        DpSize(maxWidth / cols, maxHeight / rows)
    }

    val gridOffsets = remember(rows, cols, itemSize) {
        (0 until rows).map { rowIndex ->
            (0 until cols).map { colIndex ->
                DpOffset(itemSize.width * colIndex, itemSize.height * rowIndex)
            }
        }
    }

    gridOffsets.forEach { row ->
        row.forEach { it ->
            Box(
                Modifier
                    .size(itemSize)
                    .offset(it.x, it.y)) {
                itemContent()
            }
        }
    }
}
