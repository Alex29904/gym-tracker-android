package com.example.gymtracker

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.gymtracker.ui.theme.GymTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GymTrackerTheme{
                MainPage()
            }
        }
    }
}

data class Workout(
    val id: Int,
    var name: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//Definisce la schermata iniziale
fun MainPage(){
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
                Text("+")
            }
        }

    ){ innerPadding ->
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

            WorkoutList(workouts)
        }

    }
}

@Composable
//Definisce la UI di una singola scheda di allenamento
fun WorkoutItem(workout: Workout){
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth() //Riempie tutta la riga
            .padding(horizontal = 16.dp, vertical = 5.dp), //Aggiunge spazio attorno alla scheda
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Text(
                text = workout.name,
                modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
//Definisce la UI di una lista di schede allenamento
fun WorkoutList(workouts: List<Workout>){
    LazyColumn {
        items(workouts) { workout ->
            WorkoutItem(workout)
        }
    }
}


//Definisce le UI preview per la modalità chiara e quella scura
@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode")
@Composable
fun PreviewMainPage() {
    GymTrackerTheme {
        MainPage()
    }
}