package com.example.gymtracker.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gymtracker.data.dao.ExerciseDao
import com.example.gymtracker.data.dao.WorkoutDao
import com.example.gymtracker.data.dao.WorkoutExerciseDao
import com.example.gymtracker.data.entity.Exercise
import com.example.gymtracker.data.entity.Workout
import com.example.gymtracker.data.entity.WorkoutExercise

@Database(
    entities = [Workout::class, Exercise::class, WorkoutExercise::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase(){

    abstract fun workoutDao(): WorkoutDao

    abstract fun exerciseDao(): ExerciseDao

    abstract fun workoutExerciseDao(): WorkoutExerciseDao

    companion object{

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            return INSTANCE?: synchronized(this){

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "workout_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}