package com.example.gymtracker.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gymtracker.data.entity.Workout

@Composable
//Definisce la UI di una singola scheda di allenamento
fun WorkoutItem(workout: Workout, onClick: () -> Unit, onLongClick: () -> Unit){
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth() //Riempie tutta la riga
            .padding(horizontal = 16.dp, vertical = 5.dp) //Aggiunge spazio attorno alla scheda
            .combinedClickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick,
                onLongClick = onLongClick
            ),
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