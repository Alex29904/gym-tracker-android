package com.example.gymtracker.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.gymtracker.Workout

@Composable
//Definisce la UI di una lista di schede allenamento
fun WorkoutList(workouts: List<Workout>, navController: NavController){
    LazyColumn {
        items(workouts) { workout ->
            WorkoutItem(
                workout,
                onClick = { navController.navigate("detail/${workout.id}")})
        }
    }
}