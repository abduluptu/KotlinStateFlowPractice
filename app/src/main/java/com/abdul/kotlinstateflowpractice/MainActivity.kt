package com.abdul.kotlinstateflowpractice

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.abdul.kotlinstateflowpractice.ui.theme.KotlinStateFlowPracticeTheme
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinStateFlowPracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                    FlowsPractice()
                }
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(DelicateCoroutinesApi::class)
@Composable
fun FlowsPractice() {
    //consumer
    GlobalScope.launch(Dispatchers.Main) {
        val result = producer()
       /* result.collect {
           // delay(6000)
            Log.d("soha", "Item - $it")
        }*/

        Log.d("soha", result.value.toString())
    }
}

//producer
@OptIn(DelicateCoroutinesApi::class)
private fun producer(): StateFlow<Int> { //Flow<Int>
    val mutableStateFlow = MutableStateFlow(10)
    GlobalScope.launch {
        delay(2000) //2 seconds
        mutableStateFlow.emit(20)
        delay(2000) //2 seconds
        mutableStateFlow.emit(30)
    }
    return mutableStateFlow
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotlinStateFlowPracticeTheme {
        Greeting("Android")
    }
}