package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Surface (modifier = Modifier.fillMaxSize()) {
                    LemonadeScreen()
                }
            }
        }
    }
}

@Composable
fun LemonadeScreen() {
    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        Header(title = "Lemonade")

        Body(modifier = Modifier.weight(1F))
    }
}

@Composable
fun Header(
    title: String,
    modifier: Modifier = Modifier
) {
    Text (
        text = title,
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        letterSpacing = 5.sp,
        modifier = Modifier.fillMaxWidth()
            .background(colorResource(R.color.lemon))
            .padding(32.dp)
    )
}

@Composable
fun Body(
    modifier: Modifier = Modifier
) {
    var level by remember { mutableStateOf(1) }
    var squeezing by remember { mutableStateOf(0) }

    val image = when(level) {
        1 -> painterResource(R.drawable.lemon_tree)
        2 -> painterResource(R.drawable.lemon_squeeze)
        3 -> painterResource(R.drawable.lemon_drink)
        else -> painterResource(R.drawable.lemon_restart)
    }

    val instruction = when(level) {
        1 -> stringResource(R.string.lemon_tree)
        2 -> stringResource(R.string.fresh_lemon)
        3 -> stringResource(R.string.fresh_lemonade)
        else -> stringResource(R.string.empty_glass)
    }

    val description = when(level) {
        1 -> stringResource(R.string.lemon_tree_description)
        2 -> stringResource(R.string.fresh_lemon_description)
        3 -> stringResource(R.string.fresh_lemonade_description)
        else -> stringResource(R.string.empty_glass_description)
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button (
            onClick = {
                if (level == 1) {
                    squeezing = (2..4).random()
                    level++
                }
                else if (level == 2) {
                    squeezing--
                    if (squeezing == 0) level++
                }
                else if (level == 4) {
                    level = 1
                } else {
                    level++
                }
            },
            colors = ButtonDefaults.buttonColors(Color(0XFF74b9ff)),
            shape = RoundedCornerShape(20)
        ) {
            Image(
                painter = image,
                contentDescription = description
            )
        }
        Spacer (modifier = Modifier.height(28.dp))
        Text (
            text = instruction,
            fontSize = 20.sp
        )
    }
}

@Preview
@Composable
fun LemonadeAppPreview() {
    LemonadeScreen()
}