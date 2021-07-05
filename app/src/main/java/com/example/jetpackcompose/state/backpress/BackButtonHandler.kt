package com.example.jetpackcompose.state.backpress

import android.content.ContextWrapper
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext

/**
 * Related discussion -
 * https://kotlinlang.slack.com/archives/CJLTWPH7S/p1591558155394500?thread_ts=1591558024.394400&cid=CJLTWPH7S
 */

// We create a static Ambient of the type OnBackPressedDispatcherOwner. This was present even in 
// Classic Android and we will make use of this dispatcher to power our back press handling. 

// What are Ambients?
// In Compose, we typically pass data through the composition tree explicitly through means of
// parameters to composable functions. This is inline with the principles of unidirection 
// data flow that Compose heavily recommends using. There are situations where this won't 
// always be possible. For these cases, [Ambient]s can be used as an implicit way to have 
// data flow through a composition.

// Another way to think about Providers is that I can get access to a value in the middle of 
// a composition, without having to pass the value in. Some other examples of Providers and 
// Ambients are AmbientContext(to get access to the context), CoroutineAmbientContext, etc. 
private val AmbientBackPressedDispatcher =
    staticCompositionLocalOf<OnBackPressedDispatcherOwner?> { null }

// Simple implementation of OnBackPressedCallback interface. Holds a reference to a lambda that's
// used to describe the onBackPressed action and calls it at the right instance (when  
// handleOnBackPressed is called)
private class ComposableBackHandler(enabled: Boolean) : OnBackPressedCallback(enabled) {
    lateinit var onBackPressed: () -> Unit

    override fun handleOnBackPressed() {
        onBackPressed()
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should 
// think of composable functions to be similar to lego blocks - each composable function is in turn 
// built up of smaller composable functions.
@Composable
internal fun handler(
    enabled: Boolean = true,
    onBackPressed: () -> Unit
) {
    val dispatcher = (AmbientBackPressedDispatcher.current ?: return).onBackPressedDispatcher
    // remember{} is a helper composable that calculates the value passed to it only during the 
    // first composition. It then returns the same value for every subsequent composition. In the 
    // example below, it initializes the value of ComposableBackHandler() and does it only during 
    // the first composition. It's important to understand that the subsequent screens where this 
    // value is passed to are still allowed to modify the value (depending on whether it has 
    // mutable properties).  
    val handler = remember { ComposableBackHandler(enabled) }
    // The onCommmit block is a lifecycle effect composable that is called every time this 
    // composition is committed. In simpler words, the first onCommit block below will run each 
    // time the value of dispatcher changes (in addition to the first time the handler composable
    // is called)
    DisposableEffect(dispatcher) {
        dispatcher.addCallback(handler)
        // The onDispose block inside the onCommit effect is called to do any clean up(if 
        // necessary) for the side effect that executed inside onCommit. 
        onDispose { handler.remove() }
    }
    LaunchedEffect(enabled) {
        handler.isEnabled = enabled
    }
    LaunchedEffect(onBackPressed) {
        handler.onBackPressed = onBackPressed
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should 
// think of composable functions to be similar to lego blocks - each composable function is in turn 
// built up of smaller composable functions.
@Composable
internal fun BackButtonHandler(onBackPressed: () -> Unit) {
    // Providers allows you to map values to Ambients. You can think of binding values to a key 
    // represented by Ambients. 

    // What are Ambients?
    // In Compose, we typically pass data through the composition tree explicitly through means of
    // parameters to composable functions. This is inline with the principles of unidirection 
    // data flow that Compose heavily recommends using. There are situations where this won't 
    // always be possible. For these cases, [Ambient]s can be used as an implicit way to have 
    // data flow through a composition.

    // Another way to think about Providers is that I can get access to a value in the middle of 
    // a composition, without having to pass the value in. Some other examples of Providers and 
    // Ambients are AmbientContext(to get access to the context), CoroutineAmbientContext, etc. 
    var context = LocalContext.current
    // Inspired from https://cs.android.com/androidx/platform/frameworks/support/+/
    // androidx-master-dev:navigation/navigation-compose/src/main/java/androidx/navigation/
    // compose/NavHost.kt;l=88
    // This was necessary because using Jetpack Navigation does not allow typecasting a 
    // NavBackStackEntry to LifecycleOwnerAmbient.
    while (context is ContextWrapper) {
        if (context is OnBackPressedDispatcherOwner) {
            break
        }
        context = context.baseContext
    }
    CompositionLocalProvider(
        AmbientBackPressedDispatcher provides context as ComponentActivity
    ) {
        handler {
            onBackPressed()
        }
    }
}
