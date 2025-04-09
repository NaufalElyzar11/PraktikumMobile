package com.example.praktiikum1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.praktiikum1.ui.theme.Praktiikum1Theme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Praktiikum1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DiceRollerApp()
                }
            }
        }
    }
}

@Preview
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
        .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    var result1 by remember { mutableStateOf( 0) }
    var result2 by remember { mutableStateOf( 0) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val imageResource1 = when(result1) {
        0 -> R.drawable.dice_0
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    val imageResource2 = when(result2) {
        0 -> R.drawable.dice_0
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                Snackbar(snackbarData = data, containerColor = Color.LightGray, contentColor = Color.Black)
            }
        }
    ) { padding ->

        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(150.dp))

            Row(modifier = Modifier.padding(16.dp)) {
                Image(
                    painter = painterResource(imageResource1),
                    contentDescription = result1.toString()
                )

                Image(
                    painter = painterResource(imageResource2),
                    contentDescription = result2.toString()
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = {
                result1 = (1..6).random()
                result2 = (1..6).random()
                if (result1 == result2) {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Selamat, anda dapat dadu double!")
                    }
                }
            }) {
                Text(stringResource(R.string.roll), fontSize = 24.sp)
            }
        }
    }
}