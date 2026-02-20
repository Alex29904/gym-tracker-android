package com.example.gymtracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.gymtracker.data.database.AppDatabase
import com.example.gymtracker.data.entity.Workout
import com.example.gymtracker.data.repository.WorkoutRepository
import kotlinx.coroutines.launch

class WorkoutViewModel(application: Application) : AndroidViewModel(application) {

    //Ottiene il database
    private val database = AppDatabase.getDatabase(application)

    //Ottiene la repository
    private val repository = WorkoutRepository(database.workoutDao())

    //Lista allenamenti
    val workouts = repository.allWorkouts.asLiveData()

    //Inserisce nuovo workout
    fun insertWorkout(name: String) {
        viewModelScope.launch {
            repository.insert(Workout(name = name))
        }
    }

    //Elimina workout
    fun deleteWorkout(workout: Workout) {
        viewModelScope.launch {
            repository.delete(workout)
        }
    }

    //Rinomina workout
    fun updateWorkout(workout: Workout) {
        viewModelScope.launch {
            repository.update(workout)
        }
    }
}