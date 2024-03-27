package com.axondragonscale.tzfe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
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
    Text("2048")
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TZFETheme {
        GameScreen()
    }
}