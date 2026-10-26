package com.example.module_3.Module_3_7.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.module_3.Module_3_7.data.TodoItem


@Composable
fun TodoDetailScreen(
    todoItem: TodoItem?,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            Button(
                onClick = onBack,
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(start = 8.dp)
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 12.dp)
        ) {
            if (todoItem != null) {
                Text(
                    text = todoItem.title,
                    fontSize = 28.sp,
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = todoItem.description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = if (todoItem.isCompleted) "Выполнено" else "Не выполнено",
                    color = if (todoItem.isCompleted) Color(0xFF2E7D32) else Color.Red,
                    style = MaterialTheme.typography.bodyLarge
                )
            } else {
                Text("Задача не найдена")
            }
        }
    }
}