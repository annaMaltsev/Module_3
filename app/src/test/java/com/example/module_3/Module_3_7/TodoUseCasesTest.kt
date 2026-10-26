package com.example.module_3.Module_3_7

import com.example.module_3.Module_3_7.data.TodoDataSource
import com.example.module_3.Module_3_7.data.TodoItemDto
import com.example.module_3.Module_3_7.data.repository.TodoRepositoryImpl
import com.example.module_3.Module_3_7.domain.GetTodosUseCase
import com.example.module_3.Module_3_7.domain.ToggleTodoUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

// Подменяет чтение todos.json: на компьютере нет Context и assets
class FakeTodoDataSource : TodoDataSource {
    override fun getTodos(): List<TodoItemDto> = listOf(
        TodoItemDto(1, "Купить молоко", "2 литра, обезжиренное", isCompleted = false),
        TodoItemDto(2, "Позвонить маме", "Спросить про выходные", isCompleted = true),
        TodoItemDto(3, "Сделать ДЗ по Android", "Clean Architecture + Compose", isCompleted = false)
    )
}

class TodoUseCasesTest {

    private lateinit var getTodosUseCase: GetTodosUseCase
    private lateinit var toggleTodoUseCase: ToggleTodoUseCase

    @Before
    fun setUp() {
        val repository = TodoRepositoryImpl(FakeTodoDataSource())
        getTodosUseCase = GetTodosUseCase(repository)
        toggleTodoUseCase = ToggleTodoUseCase(repository)
    }

    // 1) GetTodosUseCase возвращает 3 задачи
    @Test
    fun getTodos_returnsThreeTodos() = runBlocking {
        val todos = getTodosUseCase()

        assertEquals(3, todos.size)
    }

    // 2) toggleTodo меняет isCompleted
    @Test
    fun toggleTodo_changesIsCompleted() = runBlocking {
        val before = getTodosUseCase().first { it.id == 1 }
        assertFalse(before.isCompleted)

        toggleTodoUseCase(1)
        val afterFirstToggle = getTodosUseCase().first { it.id == 1 }
        assertTrue(afterFirstToggle.isCompleted)

        toggleTodoUseCase(1)
        val afterSecondToggle = getTodosUseCase().first { it.id == 1 }
        assertFalse(afterSecondToggle.isCompleted)
    }
}
