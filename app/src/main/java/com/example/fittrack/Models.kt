package com.example.fittrack

import androidx.compose.ui.graphics.Color


enum class Difficulty(val label: String, val color: Color) {
    BEGINNER("Beginner", Color(0xFF00999E)), INTERMEDIATE(
        "Intermediate",
        Color(0xFF212121)
    ),
    ADVANCED(
        "Advanced",
        Color(0xFFC80F2E)
    )

}


data class Exercise(
    val id: Int,
    val category: String,
    val name: String,
    val difficulty: Difficulty,
    val preview: String,
    val steps: List<String>,
    val recommendedMinutes: Int
)

data class WorkoutLogEntry(
    val id: Long,
    val exerciseName: String,
    val category: String,
    val minutes: Int,
    val timestamp: String
)

data class WorkoutUiState(
    val totalWorkouts: Int = 0,
    val totalMinutes: Int = 0,
    val history: List<WorkoutLogEntry> = emptyList()
)

object ExerciseRepository {
    val categories = listOf("Strength", "Cardio", "Flexibility", "HIIT")

    val exercises = listOf(

        Exercise(
            1, "Strength", "Squat", Difficulty.BEGINNER,
            "Position yourself having your feet hip-width apart.",
            listOf(
                "Once in position bend your knees and lower your hips down.",
                "Keep your chest up when performing a squat.",
                "Make sure to have your heels flat on the floor.",
                "Repeat for 3 sets of 10 reps."
            ), 10
        ),

        Exercise(
            2, "Strength", "Romanian Deadlifts", Difficulty.INTERMEDIATE,
            "First grab your choice of weighted dumbbells.",
            listOf(
                "Begin to hinge at your hips with a soft knee bend.",
                "Lower the weights down to your shins.",
                "Then come back up and repeat."
            ), 20
        ),
        Exercise(
            3, "Strength", "Plyometric Push-Ups", Difficulty.ADVANCED,
            "Begin to position yourself in a high plank position.",
            listOf(
                "Bend your elbows to lower your chest toward the floor.",
                "Push into the ground as fast and hard as possible.",
                "Drive your upper body upward with enough force that your hands completely leave the floor.",
                "Catch yourself on the way down by landing with slight bent elbows to absorb impact."
            ), 20
        ),
        Exercise(
            4, "Cardio", "Walking", Difficulty.BEGINNER,
            "Find a suitable location and begin to walk at a steady pace.",
            listOf(
                "Stand tall with relaxed shoulders.",
                "Make sure your arms are swinging naturally as you walk.",
                "Maintain a steady pace for the entire walk."
            ), 30
        ),

        Exercise(
            5, "Cardio", "Stair Climbing", Difficulty.INTERMEDIATE,
            "Go to your local gym and locate the step machines near the cardio section of the gym.",
            listOf(
                "Once finding a machine, set the machine at setting that is suitable for your needs.",
                "Place your entire foot flat at each step.",
                "Climb slowly and steadily."
            ), 30
        ),

        Exercise(
            6, "Cardio", "Burpees", Difficulty.ADVANCED,
            "Stand with your feet shoulder width apart and your arms at your sides.",
            listOf(
                "Drop down into a squat position and place your hands flat on the floor.",
                "Step your feet back into a high plank position, keep your body in a straight line.",
                "Lower your chest and thighs all the way to the floor and then push back up into a plank.",
                "Step your feet forward toward the outside of your hands.",
                "Drive through your heels and jump vertically into the air with your arms reaching overhead."
            ), 25
        ),

        Exercise(
            7, "Flexibility", "Standing Hamstring Stretch", Difficulty.BEGINNER,
            "Stand with feet hip width apart.",
            listOf(
                "Hinge forward gently at your hips.",
                "Reach toward your shins or toes to stretch the back of your thighs and lower back.",
                "Hold for 30 seconds."
            ), 10
        ),


        Exercise(
            8, "Flexibility", "Lizard Pose", Difficulty.INTERMEDIATE,
            "Begin in a low lunge.",
            listOf(
                "Step your right foot forward to the outside edge of your right hand.",
                "Lower your back knee. Untuck your left toes and rest your left knee on the floor.",
                "Slide your left leg backward slightly until you feel a comfortable stretch.",
                "Lower your forearms. Keep your spine long and chest open as you begin to walk your hands forward.",
                "Sink your hips forward and down. Hold your posture for 5 to 10 deep breaths before switching sides."
            ), 15
        ),


        Exercise(
            9, "Flexibility", "Pancake Stretch", Difficulty.ADVANCED,
            "Begin by sitting wide on the floor.",
            listOf(
                "Start spreading your legs into a comfortable V-shape straddle.",
                "Rotate your pelvis forward, keeping your spine completely straight and chest lifted.",
                "Reach your hands out and slowly crawl them forward along the floor between your legs.",
                "Pull your belly and chest down toward the ground without rounding your lower back."
            ), 15
        ),

        Exercise(
            10, "HIIT", "Mountain Climbers", Difficulty.BEGINNER,
            "Start in a high plank position.",
            listOf(
                "Gently drive your knees forward toward your chest.",
                "Repeat the steps in a steady and controlled pace.",
            ), 8
        ),


        Exercise(
            11, "HIIT", "Jump Squats", Difficulty.INTERMEDIATE,
            "Lower into a standard squat.",
            listOf(
                "Make sure to keep your back straight.",
                "Explode upward into a vertical jump, landing softly on your mid foot.",
                "Repeat this exercise doing 3 sets with reps of 10."
            ), 15
        ),


        Exercise(
            12, "HIIT", "Plank Jacks with Push-Ups", Difficulty.ADVANCED,
            "Begin by holding a high plank position.",
            listOf(
                "Perform a push-up.",
                "Jump with your feet out and in like a horizontal jumping jack.",
                "Keep a core rigid."
            ), 15
        ),
    )

    fun forCategory(category: String) = exercises.filter { it.category == category }
    fun byId(id: Int) = exercises.find { it.id == id }

}





