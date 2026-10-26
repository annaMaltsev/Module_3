package com.example.module_3.Module_3_5

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.dropUnlessResumed
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

@Serializable
object Home


@Serializable
object Details
@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Home
    ){
        composable<Home> { FirstScreen(
            onNavigateToProfile = dropUnlessResumed{
                navController.navigate(Details)
            }
        ) }
        composable<Details> { SecondScreen(
            onBackClick = dropUnlessResumed{
                navController.popBackStack()
            }
        ) }
    }
}