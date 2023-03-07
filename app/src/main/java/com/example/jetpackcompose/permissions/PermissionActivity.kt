package com.example.jetpackcompose.permissions

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

class PermissionActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}

/**
 * In this example, we are going to use Google's Accompanist Library - Jetpack Compose Permissions,
 * to request for the permissions from the user.
 * Reference: https://google.github.io/accompanist/permissions/
 *
 * Please Note: The version of the library depends on the version of the Jetpack Compose version
 * that you're using in the application. So, please make sure that the Accompanist library version
 * matches the Jetpack Compose version.
 * Version Reference: https://github.com/google/accompanist#compose-versions
 */
@Composable
private fun App() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        val contactsPermissionState = rememberPermissionState(
            permission = Manifest.permission.READ_CONTACTS
        )

        if (contactsPermissionState.status.isGranted) {
            Text("Contacts permission is granted")
        } else {
            Column {
                val text = if (contactsPermissionState.status.shouldShowRationale) {
                    // Shown if the user has denied the requested permissions but
                    // the rationale can be shown, informing the user why the permission
                    // is needed.
                    "Contacts permission is required to display list of contact."
                } else {
                    // Shown if it is the first time the user reached this page, and
                    // the app is requesting the permission for the first time.
                    "Please grant the Contacts permission to display list of contacts."
                }

                Text(text)
                Button(
                    onClick = { contactsPermissionState.launchPermissionRequest() },
                ) {
                    Text("Request Permission")
                }
            }
        }
    }
}