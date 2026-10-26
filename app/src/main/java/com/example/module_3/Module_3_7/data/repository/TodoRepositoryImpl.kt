package com.example.module_3.Module_3_7.data.repository

import com.example.module_3.Module_3_7.data.TodoItem
import com.example.module_3.Module_3_7.data.TodoDataSource
import com.example.module_3.Module_3_7.domain.TodoRepository

class TodoRepositoryImpl(
    private val dataSource: TodoDataSource
): TodoRepository {
    private val cachedTodos = mutableListOf<TodoItem>()
    override suspend fun getTodos(): List<TodoItem> {
        if (cachedTodos.isEmpty()){
            val dtos = dataSource.getTodos()
            cachedTodos.addAll(
                dtos.map { dto ->
                    TodoItem(
                        id = dto.id,
                        title = dto.title,
                        description = dto.description,
                        isCompleted = dto.isCompleted
                    )
                }
            )
        }
        return cachedTodos.toList()
    }

    override suspend fun toggleTodo(id: Int) {
        val index = cachedTodos.indexOfFirst { it.id == id }
        if (index != -1){
            val item = cachedTodos[index]
            cachedTodos[index] = item.copy(isCompleted = !item.isCompleted)
        }
    }
}