package com.example.jetpackcompose.navigation.navigation_compose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpackcompose.navigation.Datasource
import com.example.jetpackcompose.navigation.Task
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ComposeNavigationWithArgActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeNavigationWithArgApp()
        }
    }
}

@Composable
fun ComposeNavigationWithArgApp(
    navController: NavHostController = rememberNavController()
) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val canNavigateBack by remember(backStackEntry) {
        mutableStateOf(navController.previousBackStackEntry != null )
    }

    Scaffold(
        topBar = {
            MyTopAppBar(
                title = "Tasks",
                canNavigateBack = canNavigateBack,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding) // #1
        ) {
            // or you can directly pass the modifier(#1) to AppNavHost(..)
            AppNavHost(navController)
        }
    }
}

@Composable
private fun MyTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        modifier = modifier,
        navigationIcon = if (canNavigateBack) {
            {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        } else null,
    )
}

@Composable
private fun AppNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = "tasks",
    ) {
        composable(route = "tasks") {
            TaskListScreen(
                tasks = Datasource.getAllTasks(),
                onTaskClick = { task -> navController.navigate("tasks/${task.id}") }
            )
        }

        composable(
            route = "tasks/{taskId}",
            arguments = listOf(navArgument("taskId") { type = NavType.StringType }),
        ) { backStackEntry ->
            TaskDetails(
                taskId = backStackEntry.arguments?.getString("taskId"),
            )
        }
    }
}

@Composable
private fun TaskListScreen(
    tasks: List<Task>,
    onTaskClick: (Task) -> Unit,
) {

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        stickyHeader {
            Text(
                text = "Tasks",
                style = MaterialTheme.typography.h6
            )
        }

        items(tasks) { task ->
            TaskItem(task = task, onItemClick = onTaskClick)
        }
    }
}

@Composable
private fun TaskItem(
    task: Task,
    onItemClick: (Task) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = {
            onItemClick(task)
        }
    ) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Max)
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(4.dp)
                    .background(Color(0xFFFFAA00))
            )

            Row(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = task.title, modifier = Modifier.weight(1f))
            }
        }
    }
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
@Suppress("UnusedPrivateMember", "MagicNumber")
private fun DetailViewComposeNavigationActivityPreview() {
    MaterialTheme {
        Surface {
            ComposeNavigationWithArgApp()
        }
    }
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
@Suppress("UnusedPrivateMember", "MagicNumber")
private fun TaskDetailsPreview() {
    MaterialTheme {
        Surface {
            TaskDetails(
                taskId = "1"
            )
        }
    }
}

@Composable
private fun TaskDetails(
    taskId: String?,
) {

    val task by remember { mutableStateOf(Datasource.findTaskById(taskId)) }

    if (task == null) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Task with id $taskId not found!",
                style = MaterialTheme.typography.h6
            )
        }
    } else {
        // you should avoid using `!!`,
        // the data should be wrapped in the ViewState class for type safety.
        TaskDetails(task!!)
    }
}

@Composable
private fun TaskDetails(task: Task) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Card(
            backgroundColor = Color(0xFFF3F2F2)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "#${task.id}",
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                )
                TitleAndLabel(title = "Title", label = task.title)
                TitleAndLabel(title = "Description", label = task.description)
                TitleAndLabel(title = "Created On", label = task.timestamp)
            }
        }
    }
}

@Composable
private fun TitleAndLabel(
    title: String,
    label: String?,
) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
        )
        Text(
            text = label ?: "-",
            style = MaterialTheme.typography.body1
        )
    }
}

@Composable
private fun TitleAndLabel(
    title: String,
    label: Date?,
) {
    val simpleDateTimeFormatter = SimpleDateFormat("EEE, dd MMMM yyyy HH:mm", Locale.getDefault())
    val formattedTimestamp =
        if (label != null) simpleDateTimeFormatter.format(label)
        else "-"

    TitleAndLabel(
        title = title,
        label = formattedTimestamp
    )
}