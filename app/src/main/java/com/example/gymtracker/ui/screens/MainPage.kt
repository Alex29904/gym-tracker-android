package com.example.gymtracker.ui.screens

import com.example.gymtracker.ui.components.WorkoutList
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gymtracker.data.Workout
import com.example.gymtracker.ui.theme.GymTrackerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//Definisce la schermata iniziale
fun MainPage(navController: NavController){
    GymTrackerTheme {
        var workouts by remember { mutableStateOf(listOf<Workout>()) }

        Scaffold(
            topBar = {
                TopAppBar(
                    colors = topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text("I tuoi allenamenti")
                    }
                )
            },

            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        val newWorkout = Workout(
                            id = workouts.size,
                            name = "Allenamento ${workouts.size + 1}"
                        )
                        workouts += newWorkout
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Aggiungi allenamento"
                    )
                }
            }
        ){
                innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding) //Padding automatico della TopBar
                    .padding(top = 20.dp) //Padding aggiuntivo
                    .fillMaxSize()
            ) {
                if(workouts.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        BiasAlignment(horizontalBias = 0f, verticalBias = -0.3f)
                    ) {
                        Text(
                            text = "Aggiungi un allenamento premendo '+' in basso",
                            modifier = Modifier.padding(6.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                WorkoutList(workouts, navController)
            }

        }
    }
}