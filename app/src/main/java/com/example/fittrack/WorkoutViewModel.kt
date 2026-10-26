package com.example.fittrack

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class WorkoutViewModel : ViewModel() {
    var uiState by mutableStateOf(WorkoutUiState())
        private set


    private var nextId = 0L

    fun logWorkout(exercise: Exercise, minutes: Int) {
        val time = SimpleDateFormat("MMM d, h:mm a", Locale.getDefault()).format(Date())
        val entry = WorkoutLogEntry(
            id = nextId++,
            exerciseName = exercise.name,
            category = exercise.category,
            minutes = minutes,
            timestamp = time
        )

        uiState = uiState.copy(
            totalWorkouts = uiState.totalWorkouts + 1,
            totalMinutes = uiState.totalMinutes + minutes,
            history = listOf(entry) + uiState.history
        )

    }


    fun clearLogs() {
        uiState = WorkoutUiState()
    }


}