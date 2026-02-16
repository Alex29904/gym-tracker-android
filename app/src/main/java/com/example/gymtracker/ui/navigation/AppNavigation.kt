package com.example.gymtracker.ui.navigation

import com.example.gymtracker.ui.screens.MainPage
import com.example.gymtracker.ui.screens.WorkoutDetailPage
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AppNavigation(){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "list"
    ) {
        composable("list") {
            MainPage(navController)
        }

        composable(
            route = "detail/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) { backStackEntry ->

            val workoutId = backStackEntry.arguments?.getInt("id")
            WorkoutDetailPage(workoutId)
        }
    }
}