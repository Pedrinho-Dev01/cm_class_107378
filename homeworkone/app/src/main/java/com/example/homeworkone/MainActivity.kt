package com.example.homeworkone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class WatchlistItem(
    val id: Int = 0, // Unique identifier
    val title: String,
    val type: String, // "Movie" or "Series"
    var isWatched: Boolean = false
)

class WatchlistViewModel : ViewModel() {
    private val _watchlistItems = MutableStateFlow<List<WatchlistItem>>(
        listOf(
            WatchlistItem(1, "The Shawshank Redemption", "Movie"),
            WatchlistItem(2, "Stranger Things", "Series"),
        )
    )
    val watchlistItems: StateFlow<List<WatchlistItem>> = _watchlistItems

    fun addItem(title: String, type: String) {
        val newId = (_watchlistItems.value.maxOfOrNull { it.id } ?: 0) + 1
        val newItems = _watchlistItems.value + WatchlistItem(newId, title, type)
        _watchlistItems.value = newItems
    }

    fun markAsWatched(id: Int, isWatched: Boolean) {
        val updatedItems = _watchlistItems.value.map { item ->
            if (item.id == id) {
                item.copy(isWatched = isWatched)
            } else {
                item
            }
        }
        _watchlistItems.value = updatedItems
    }

    fun deleteItem(id: Int) {
        _watchlistItems.value = _watchlistItems.value.filter { it.id != id }
    }
}

@Composable
fun WatchlistScreen(viewModel: WatchlistViewModel) {

    val lifecycleOwner = LocalLifecycleOwner.current
    val items by viewModel.watchlistItems.collectAsStateWithLifecycle(lifecycleOwner)

    var showDialog by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf("") }
    val type by remember { mutableStateOf("Movie") }

    LazyColumn {
        items(items) { item ->
            WatchlistItemRow(item, viewModel)
        }
    }

    Button(onClick = { showDialog = true }) {
        Text("Add Item")
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Add New Item") },
            text = {
                Column {
                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text("Title") }
                    )
                    Spacer(Modifier.height(8.dp))
                    // Dropdown or radio buttons for selecting type (Movie/Series)
                }
            },
            confirmButton = {
                Button(onClick = {
                    viewModel.addItem(title, type)
                    showDialog = false
                }) {
                    Text("Add")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun WatchlistItemRow(item: WatchlistItem, viewModel: WatchlistViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = item.isWatched,
            onCheckedChange = { viewModel.markAsWatched(item.id, it) }
        )
        Text(text = "${item.title} (${item.type})")
        Spacer(Modifier.weight(1f))
        IconButton(onClick = { viewModel.deleteItem(item.id) }) {
            Icon(Icons.Filled.Delete, contentDescription = "Delete")
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: WatchlistViewModel by viewModels()
        setContent {
            WatchlistScreen(viewModel)
        }
    }
}