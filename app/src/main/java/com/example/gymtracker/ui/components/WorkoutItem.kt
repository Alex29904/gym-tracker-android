package com.example.gymtracker.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gymtracker.Workout

@Composable
//Definisce la UI di una singola scheda di allenamento
fun WorkoutItem(workout: Workout, onClick: () -> Unit){
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth() //Riempie tutta la riga
            .padding(horizontal = 16.dp, vertical = 5.dp) //Aggiunge spazio attorno alla scheda
            .clickable { onClick() }, //Se viene premuta esegue onClick()
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