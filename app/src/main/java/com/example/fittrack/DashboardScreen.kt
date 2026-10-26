package com.example.fittrack

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TopAppBar
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    uiState: WorkoutUiState, onCategoryClick: (String) -> Unit, onClearLogs: () -> Unit
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("FitTrack") }) }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SummaryCard(uiState)

            Text("Categories", style = MaterialTheme.typography.titleMedium)
            ExerciseRepository.categories.chunked(2).forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    rowItems.forEach { category ->
                        Card(
                            onClick = { onCategoryClick(category) },
                            modifier = Modifier.weight(1f),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.secondaryContainer
                            )
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(64.dp),
                                contentAlignment = Alignment.Center

                            ) {
                                Text(category, fontWeight = FontWeight.SemiBold)
                            }
                        }

                    }
                }

            }

            Text("Recent Activity", style = MaterialTheme.typography.titleMedium)

            if (uiState.history.isEmpty()) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No workouts logged yet. Select a category above to get started!")
                }
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(
                        items = uiState.history, key = { entry -> entry.id }) { entry ->
                        Card(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(entry.exerciseName, fontWeight = FontWeight.SemiBold)
                                Text("${entry.category} • ${entry.minutes} min")
                                Text(entry.timestamp, style = MaterialTheme.typography.bodySmall)
                            }
                        }
                    }
                }
            }

            Button(
                onClick = onClearLogs, modifier = Modifier.fillMaxWidth()
            ) {
                Text("Clear Logs")
            }
        }
    }
}

@Composable
fun SummaryCard(uiState: WorkoutUiState) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    uiState.totalWorkouts.toString(),
                    style = MaterialTheme.typography.headlineMedium
                )
                Text("Workouts")
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    uiState.totalMinutes.toString(), style = MaterialTheme.typography.headlineMedium
                )
                Text("Total Minutes")
            }
        }
    }
}
