package com.example.module_3.Module_3_7.domain

import com.example.module_3.Module_3_7.data.TodoItem

class GetTodosUseCase(private val repository: TodoRepository) {
    suspend operator fun invoke(): List<TodoItem> = repository.getTodos()
}
