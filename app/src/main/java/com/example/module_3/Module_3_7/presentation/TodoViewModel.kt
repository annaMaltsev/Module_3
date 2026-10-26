package com.example.module_3.Module_3_7.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.module_3.Module_3_7.data.TodoItem
import com.example.module_3.Module_3_7.data.TodoJsonDataSource
import com.example.module_3.Module_3_7.data.repository.TodoRepositoryImpl
import com.example.module_3.Module_3_7.domain.GetTodosUseCase
import com.example.module_3.Module_3_7.domain.ToggleTodoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TodoViewModel(
    private val getTodosUseCase: GetTodosUseCase,
    private val toggleTodoUseCase: ToggleTodoUseCase
) : ViewModel() {

    private val _todos = MutableStateFlow<List<TodoItem>>(emptyList())
    val todos: StateFlow<List<TodoItem>> = _todos.asStateFlow()

    init {
        viewModelScope.launch {
            _todos.value = getTodosUseCase()
        }
    }

    fun toggleTodo(id: Int) {
        viewModelScope.launch {
            toggleTodoUseCase(id)
            _todos.value = getTodosUseCase()
        }
    }

    fun getTodo(id: Int): TodoItem? = _todos.value.find { it.id == id }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val context = this[APPLICATION_KEY]!!
                val repository = TodoRepositoryImpl(TodoJsonDataSource(context))
                TodoViewModel(
                    getTodosUseCase = GetTodosUseCase(repository),
                    toggleTodoUseCase = ToggleTodoUseCase(repository)
                )
            }
        }
    }
}
