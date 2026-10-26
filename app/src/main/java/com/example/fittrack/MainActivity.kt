package com.example.fittrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fittrack.ui.theme.FitTrackTheme

object Routes {

    const val DASHBOARD = "dashboard"
    const val EXERCISES = "exercises/{category}"
    const val DETAIL = "detail/{exerciseId}"
    fun exercises(category: String) = "exercises/$category"
    fun detail(id: Int) = "detail/$id"


}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FitTrackTheme {
                FitTrackApp()
            }
        }
    }
}

@Composable
fun FitTrackApp() {
    val navController = rememberNavController()
    val viewModel: WorkoutViewModel = viewModel()


    NavHost(navController = navController, startDestination = Routes.DASHBOARD) {
        composable(route = Routes.DASHBOARD) {
            DashboardScreen(
                uiState = viewModel.uiState,
                onCategoryClick = { category -> navController.navigate(Routes.exercises(category)) },
                onClearLogs = { viewModel.clearLogs() }
            )
        }

        composable(
            route = Routes.EXERCISES,
            arguments = listOf(navArgument("category") { type = NavType.StringType })

        ) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category") ?: ""
            ExerciseListScreen(
                category = category,
                onExerciseClick = { id -> navController.navigate(Routes.detail(id)) },
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(
            route = Routes.DETAIL,
            arguments = listOf(navArgument("exerciseId") { type = NavType.IntType })
        ) { backStackEntry ->
            val exerciseId = backStackEntry.arguments?.getInt("exerciseId") ?: 0
            ExerciseDetailScreen(
                exerciseId = exerciseId,
                onLogWorkout = { exercise, minutes -> viewModel.logWorkout(exercise, minutes) },
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }

}
