package com.example.module_3.Module_3_7.domain

import com.example.module_3.Module_3_7.data.TodoItem

interface TodoRepository {
    suspend fun getTodos(): List<TodoItem>
    suspend fun toggleTodo(id: Int)

}