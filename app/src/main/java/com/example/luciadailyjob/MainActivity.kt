package com.example.luciadailyjob

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.luciadailyjob.ui.theme.LuciaDailyJobTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myViewModel: MainViewModel by viewModels {
            MainViewModelFactory((application as LuciaJobApplication).repository)
        }
        setContent {

            LuciaDailyJobTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Lucia", myViewModel)
                }
            }
        }
    }
}

class FinishStatus {
    companion object {
        const val NOT_DONE = 0
        const val DONE = 1
    }
}

class ItemType {
    companion object {
        const val GUSHI = 0
        const val RUISI = 1
    }
}

private fun getItemName(itemId: Int): String {
    return when (itemId) {
        ItemType.GUSHI -> "古诗背诵"
        ItemType.RUISI -> "瑞思英语"
        else -> "UNKOWN"
    }
}

private fun getStatus(status: Int): String {
    return when (status) {
        FinishStatus.NOT_DONE -> "未完成"
        FinishStatus.DONE -> "已完成"
        else -> "UNKOWN"
    }
}

@Composable
fun Greeting(name: String, viewModel: MainViewModel) {
//    Text(text = "Hello $name!")
    val list = viewModel.allJobs.observeAsState().value

    Scaffold(topBar = { },
        //floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {}
            ) {
                Icon(Icons.Filled.Add, "")
            }
        }, content = {
            list?.apply {
                Column() {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        list.forEach{

                        }
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 5.dp, bottom = 5.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Text(text = "TODO ITEM")
                                Text(text = "完成状态")
                            }
                        }
                        items(list) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 5.dp, bottom = 5.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Text(text = getItemName(it.itemId))
//                    if(it.status == FinishStatus.TITLE)
//                        Text(text = getStatus(it.status))
//                    else {
                                Button(onClick = { viewModel.onButtonClick.invoke(it) }) {
                                    Text(text = getStatus(it.itemStatus))
                                }
//                    }
                            }
                        }
                    }
                }
            }
        })
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    LuciaDailyJobTheme {
//        Greeting("Android")
//    }
//}