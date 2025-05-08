package com.example.jetpackcompose.navigation.compose

import java.util.Calendar
import java.util.Date

data class Task(
    val id: Int,
    val title: String,
    val description: String? = null,
    // I haven't used LocalDateTime as de-sugaring is another concept altogether
    val timestamp: Date? = Calendar.getInstance().time,
)

// You shouldn't use static data source as it makes testing difficult.
object DataSource {

    private val tasks = listOf(
        Task(
            id = 1,
            title = "Contribute to Learn-Jetpack-Compose-By-Example",
            description = "Pick a simple issue and try to solve it.",
        ),
        Task(
            id = 2,
            title = "Binge Batman Trilogy",
        ),
        Task(
            id = 3,
            title = "Buy groceries.",
            description = "Don't forget to buy bread and butter!",
            timestamp = Calendar.getInstance().time
        ),
        Task(
            id = 4,
            title = "Clear out weeds from the backyard",
        ),
    )

    fun getAllTasks() = tasks

    fun findTaskById(id: String?): Task? {
        val taskId = id?.toIntOrNull() ?: return null
        return tasks.firstOrNull { it.id == taskId }
    }
}
