package com.example.module_3.Module_3_7.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.dropUnlessResumed
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

// Маршруты называются ...Route, чтобы не путаться с функциями экранов
@Serializable
object TodoListRoute

@Serializable
data class TodoDetailRoute(val id: Int)

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    val viewModel: TodoViewModel = viewModel(factory = TodoViewModel.Factory)

    NavHost(
        navController = navController,
        startDestination = TodoListRoute
    ){
        composable<TodoListRoute> {
            TodoListScreen(
                viewModel = viewModel,
                onNavigateToDetail = { id ->
                    navController.navigate(TodoDetailRoute(id))
                }
            )
        }
        composable<TodoDetailRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<TodoDetailRoute>()
            val todos by viewModel.todos.collectAsState()
            TodoDetailScreen(
                todoItem = todos.find { it.id == route.id },
                onBack = dropUnlessResumed { navController.popBackStack() }
            )
        }
    }
}
