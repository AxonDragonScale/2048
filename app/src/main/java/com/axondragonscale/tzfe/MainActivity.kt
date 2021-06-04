package com.axondragonscale.tzfe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.axondragonscale.tzfe.ui.theme.Colors
import com.axondragonscale.tzfe.ui.theme.TZFETheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TZFETheme {
                GameScreen()
            }
        }
    }
}

@Composable
fun GameScreen() {
    Surface(
        color = Colors.GameBackground,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            HeaderView()
            BoardView()
        }
    }
}

@Composable
fun HeaderView() {
    Column(
        modifier = Modifier.padding(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "2048",
                fontSize = 64.sp,
                fontWeight = FontWeight.Bold,
                color = Colors.dark
            )

            Spacer(modifier = Modifier.weight(1f))

            ScoreView("SCORE", 432)
            Spacer(modifier = Modifier.width(4.dp))
            ScoreView("HIGH SCORE", 845)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Join the numbers",
                fontSize = 14.sp,
                color = Colors.dark
            )

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(Colors.BoardBackground)
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                Text(
                    text = "RESET",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Colors.light
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun ScoreView(scoreText: String, scoreValue: Int) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(Colors.BoardBackground)
            .padding(vertical = 4.dp, horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = scoreText,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = Color(255, 255, 255, 200)
        )

        Text(
            text = scoreValue.toString(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Colors.light
        )
    }
}

@Composable
fun BoardView() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Colors.BoardBackground)
            .aspectRatio(1f)
            .padding(4.dp)
    ) {
        Column {
            for (i in 0..3) {
                Row {
                    for (j in 0..3) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .padding(4.dp)
                        ) {
                            TileView(Modifier, i, j)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun TileView(modifier: Modifier, i: Int, j: Int) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(4.dp))
            .background(Colors.Tile2)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text("$i $j")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TZFETheme {
        GameScreen()
    }
}