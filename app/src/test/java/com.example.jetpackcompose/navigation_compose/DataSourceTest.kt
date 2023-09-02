package com.example.jetpackcompose.navigation_compose

import com.example.jetpackcompose.navigation.compose.DataSource
import org.junit.Assert.assertEquals
import org.junit.Test

class DataSourceTest {

    @Test
    fun verifyTaskListSize() {
        val tasks = DataSource.getAllTasks()
        val tasksSize = tasks.size

        assertEquals(4, tasksSize)
    }

    @Test
    fun givenValidTaskIdThenFindTaskByIdShouldReturnTask() {
        val taskId = "2"

        val task = DataSource.findTaskById(taskId)
        assertEquals(taskId, task?.id.toString())
    }

    @Test
    fun givenInvalidTaskIdThenFindTaskByIdShouldReturnNull() {
        val taskId = "10"

        val task = DataSource.findTaskById(taskId)
        assertEquals(null, task?.id?.toString())
    }
}
