package com.example.module_3.Module_3_7.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TodoListScreen(
    viewModel: TodoViewModel,
    onNavigateToDetail: (Int) -> Unit
) {
    val todos by viewModel.todos.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(todos, key = { it.id }) { item ->
            TodoCardItem(
                item = item,
                onClick = { onNavigateToDetail(item.id) },
                onCheckedChange = { viewModel.toggleTodo(item.id) }
            )
        }
    }
}