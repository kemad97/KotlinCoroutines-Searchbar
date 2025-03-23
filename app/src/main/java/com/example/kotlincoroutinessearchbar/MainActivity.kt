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
    val itemList = listOf("Alice", "Bob", "Kerolos", "Ali", "Ahmed")
    val searchQuery = remember { MutableStateFlow("") }
    
    val filteredList by searchQuery
        .map { query -> itemList.filter { it.contains(query, ignoreCase = true) } }
        .collectAsState(initial = itemList)

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        SearchBar(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            query = text,
            onQueryChange = {
                text = it
                searchQuery.value = it
            },
            onSearch = {},
            active = true,
            onActiveChange = {},
            placeholder = { Text("Search") }
        ) {
            LazyColumn(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                items(filteredList) { item ->
                    Text(text = item, modifier = Modifier.padding(8.dp))
                }
            }
        }
    }
}

