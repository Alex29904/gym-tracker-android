package com.example.gymtracker.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.gymtracker.data.entity.Exercise
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {

    //Inserisce un esercizio
    @Insert
    suspend fun insertExercise(exercise: Exercise)

    //Ritorna tutti gli esercizi
    @Query("SELECT * FROM exercises")
    fun getAllExercises(): Flow<List<Exercise>>
}