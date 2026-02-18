package com.example.gymtracker.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workouts")
data class UserWorkout(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String
)