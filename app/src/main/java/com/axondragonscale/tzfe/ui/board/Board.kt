package com.axondragonscale.tzfe.ui.board

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.axondragonscale.tzfe.data.Matrix
import com.axondragonscale.tzfe.data.Tile
import com.axondragonscale.tzfe.ui.theme.Colors

/**
 * Created by Ronak Harkhani on 11/03/23
 */

@Composable
fun Board(matrix: Matrix) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Colors.BoardBackground)
            .aspectRatio(1f)
            .padding(4.dp)
    ) {
        EmptyGrid(rows = matrix.rows, cols = matrix.cols) {
            TileView(tile = Tile.EMPTY, shouldRenderEmptyTile = true)
        }
        AnimatedGrid(matrix = matrix) {
            TileView(tile = it)
        }
    }
}


@Composable
fun TileView(tile: Tile, shouldRenderEmptyTile: Boolean = false) {
    if (!shouldRenderEmptyTile && tile.isEmpty) return
    Box(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxSize()
            .clip(RoundedCornerShape(4.dp))
            .background(tile.tileColor)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = tile.displayText,
            fontSize = tile.fontSize,
            fontWeight = FontWeight.Bold,
            color = tile.fontColor
        )
    }
}
