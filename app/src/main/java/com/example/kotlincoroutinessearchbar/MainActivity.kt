package com.example.kotlincoroutinessearchbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { SearchScreen() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen() {

    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    val itemList = listOf("Alice", "Bob", "Kerolos", "Ali", "Ahmed")

    val filteredList by remember(text) {
        mutableStateOf(itemList.filter { it.contains(text, ignoreCase = true) })
    }



    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        SearchBar(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            query = text,
            onQueryChange = { text = it },
            onSearch = { active = false },
            active = active,
            onActiveChange = { active = it },
            placeholder = { Text("Search") }
        )
        {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(top = 16.dp)) {
                items(filteredList) { item ->
                    Text(text = item, modifier = Modifier.padding(8.dp))
                }
            }

        }

    }
}

