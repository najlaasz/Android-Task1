package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold() { innerPadding ->
                    Column (

                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)

                    ){
                        Quastions(
                            name = "the quastion is "
                        )

                        NextQuastion(Color.Red, "Wrong answer")

                    }
                }
            }
        }
    }
}

@Composable
fun Quastions(name: String, modifier: Modifier = Modifier) {
    Text(

        fontSize = 30.sp,
        text = "Q: $name?",
        modifier = modifier
    )

}

@Composable
fun QuationsButton(){
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
        Button(onClick = {}) {
            Text(text = "True")
        }

        Button(onClick = {}) {
            Text(text = "False")
        }
    }
}

@Composable
fun NextQuastion(c: Color, t: String){

    Box(

        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(CircleShape)
            .size(200.dp)
            .background(color = c)

    ){
        Text(
            modifier = Modifier.fillMaxSize()
                .background(color = Color.Blue),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = Color.White,
            text = t
            )
    }
    Button(onClick = {}) {
        Text(text = "Next Quastion")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Quastions("Android")
    }
}