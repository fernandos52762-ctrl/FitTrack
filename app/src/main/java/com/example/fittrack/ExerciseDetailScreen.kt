package com.example.fittrack

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseDetailScreen(
    exerciseId: Int,
    onLogWorkout: (Exercise, Int) -> Unit,
    onNavigateBack: () -> Unit
) {
    val exercise = ExerciseRepository.byId(exerciseId)
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(exercise?.name ?: "Exercise") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        if (exercise == null) {
            Text("Exercise not found", modifier = Modifier.padding(innerPadding).padding(16.dp))
        } else {
            var input by remember { mutableStateOf("") }
            var showDialog by remember { mutableStateOf(false) }
            val minutes = input.toIntOrNull()
            val isValid = minutes != null && minutes > 0

            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("Recommended: ${exercise.recommendedMinutes} min")
                exercise.steps.forEachIndexed { i, step -> Text("${i + 1}. $step") }

                OutlinedTextField(
                    value = input,
                    onValueChange = { input = it },
                    label = { Text("Duration (minutes)") },
                    isError = !isValid,
                    supportingText = { if (!isValid) Text("Enter a whole number greater than 0") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = { minutes?.let { onLogWorkout(exercise, it); showDialog = true; input = "" } },
                    enabled = isValid,
                    modifier = Modifier.fillMaxWidth()
                ) { Text("Log Completed Workout") }
            }


            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    confirmButton = { TextButton(onClick = { showDialog = false }) { Text("OK") } },
                    title = { Text("Workout logged") },
                    text = { Text("Your workout was added to your history") }
                )
            }
        }
    }
}



