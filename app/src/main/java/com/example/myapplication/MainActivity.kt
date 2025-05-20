package com.example.myapplication

import android.R
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlin.collections.elementAt

class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                QuizApp()

            }
        }
    }
}


val myQuestions = mapOf(
    "Kuwait is located on the continent of Africa." to false,
    "The Kuwaiti flag has four colors: red, green, white, and black." to true,
    "Kuwait is famous for having one of the largest oil reserves in the world." to true
)


@Composable
fun QuizApp(){
    Scaffold() { innerPadding ->

        var questionIndex by remember { mutableStateOf(0) }
        val questionList = myQuestions.keys.toList()
        var userValue by remember { mutableStateOf<Boolean?>(null)} // when null Not yet answerd .. at
        val correctAnswer = myQuestions.values.elementAt(questionIndex)



        Column (

            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)

        ){
            Quastions(name = questionList[questionIndex])


            // getting correct Circle Shape
            // if answer not null show circle && after each question make userValue = null
            userValue?.let {
                if(userValue == myQuestions.values.elementAt(questionIndex))
                {AnswerCircle(true)}
                else
                {AnswerCircle(false)}
            }




            AnswerButton(
                trueSelected = { userValue = true },
                falseSelected = { userValue = false }
            )
//            AnswerButton(trueSelected = true,
//                falseSelected = false)



            if (userValue != null && userValue == correctAnswer && questionIndex < questionList.size - 1)
            {
                Button(onClick = {
                    questionIndex++
                    userValue = null
                }) {
                    Text(text = "Next Question")
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
fun AnswerButton(trueSelected:()-> Unit ,
                 falseSelected:()-> Unit){
    Row (
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically) {

        Button(onClick = {trueSelected()}) {    // means button value = true
            Text(text = "True")
        }

        Button(onClick = {falseSelected() }) {
            Text(text = "False")
        }
    }
}



@Composable
fun AnswerCircle(answer: Boolean){

    val c = if (answer) Color.Green else Color.Red
    val t = if (answer) "Correct Answer" else "Wrong Answer"
    Box(

        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(CircleShape)
            .size(200.dp)
            .background(color = c)

    ){
        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .background(color = c),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = Color.White,
            text = t
        )
    }

}

//@Composable
//fun NextQuastion(userAnswer: Boolean, correctAnswer: Boolean,questionIndex: Int )
//{ if (userAnswer != null && userAnswer == correctAnswer && questionIndex < questionList.size - 1)
//{
//    Button(onClick = {
//        questionIndex++
//        userAnswer = null
//    }) {
//        Text(text = "Next Question")
//    }
//
//}}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Quastions("Android")
    }
}