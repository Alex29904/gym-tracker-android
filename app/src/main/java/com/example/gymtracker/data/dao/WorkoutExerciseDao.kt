package com.example.gymtracker.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.gymtracker.data.entity.WorkoutExercise
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutExerciseDao {

    //Inserisce un esercizio nel workout
    @Insert
    suspend fun insertWorkoutExercise(workoutExercise: WorkoutExercise)

    @Query("SELECT * FROM workout_exercises WHERE workoutId = :workoutId")
    fun getExercisesForWorkout(workoutId: Int): Flow<List<WorkoutExercise>>

    @Delete
    suspend fun deleteWorkoutExercise(workoutExercise: WorkoutExercise)
}