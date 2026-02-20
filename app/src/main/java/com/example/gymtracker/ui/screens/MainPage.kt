package com.example.gymtracker.ui.screens

import android.widget.Space
import androidx.compose.foundation.clickable
import com.example.gymtracker.ui.components.WorkoutList
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gymtracker.data.entity.Workout
import com.example.gymtracker.ui.theme.GymTrackerTheme
import com.example.gymtracker.viewmodel.WorkoutViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//Definisce la schermata iniziale
fun MainPage(navController: NavController, viewModel: WorkoutViewModel){

    val workouts by viewModel.workouts.observeAsState(emptyList())

    var selectedWorkout by remember { mutableStateOf<Workout?>(null) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    var renameText by remember { mutableStateOf("") }

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
                    viewModel.insertWorkout("Allenamento ${workouts.size+1}")
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Aggiungi allenamento"
                )
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
                        .fillMaxSize(),
                    BiasAlignment(horizontalBias = 0f, verticalBias = -0.3f)
                ) {
                    Text(
                        text = "Aggiungi un allenamento premendo '+' in basso",
                        modifier = Modifier.padding(6.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }

            WorkoutList(
                workouts,
                navController,
                onLongClick = { workout ->
                    selectedWorkout = workout
                    renameText = workout.name
                    scope.launch { sheetState.show() }
                }
            )

            if (selectedWorkout != null) {
                ModalBottomSheet(
                    onDismissRequest = { selectedWorkout = null },
                    sheetState = sheetState
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {

                        Text(
                            text = "Rinomina allenamento",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = renameText,
                            onValueChange = { renameText = it },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = {
                                val updated = selectedWorkout!!.copy(name = renameText)
                                viewModel.updateWorkout(updated)
                                selectedWorkout = null
                                scope.launch { sheetState.hide() }
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Salva")
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        HorizontalDivider()

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "Elimina allenamento",
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    viewModel.deleteWorkout(selectedWorkout!!)
                                    selectedWorkout = null
                                    scope.launch { sheetState.hide() }
                                }
                                .padding(vertical = 12.dp)
                        )
                    }
                }
            }
        }
    }
}