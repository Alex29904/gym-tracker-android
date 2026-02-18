package com.example.gymtracker.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.gymtracker.data.Workout
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao{

    //Inserisce un nuovo allenamento nel database
    @Insert
    suspend fun insertWorkout(workout: Workout)

    //Ritorna tutti gli allenamenti
    @Query("SELECT * FROM workouts")
    fun getAllWorkouts(): Flow<List<Workout>>

    //Elimina un allenamento
    @Delete
    suspend fun deleteWorkout(workout: Workout)

    //Aggiorna un allenamento
    @Update
    suspend fun updateWorkout(workout: Workout)
}