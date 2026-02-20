package com.example.gymtracker.data.repository

import com.example.gymtracker.data.dao.WorkoutDao
import com.example.gymtracker.data.entity.Workout

class WorkoutRepository(private val workoutDao: WorkoutDao) {

    val allWorkouts = workoutDao.getAllWorkouts()

    suspend fun insert(workout: Workout) {
        workoutDao.insertWorkout(workout)
    }

    suspend fun delete(workout: Workout) {
        workoutDao.deleteWorkout(workout)
    }

    suspend fun update(workout: Workout) {
        workoutDao.updateWorkout(workout)
    }
}