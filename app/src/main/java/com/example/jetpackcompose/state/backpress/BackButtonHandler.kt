package com.example.jetpackcompose.state.backpress

import android.content.ContextWrapper
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext

/**
 * Related discussion -
 * https://kotlinlang.slack.com/archives/CJLTWPH7S/p1591558155394500?thread_ts=1591558024.394400&cid=CJLTWPH7S
 */

// We create a static LocalComposition of the type OnBackPressedDispatcherOwner. This was present even in
// Classic Android and we will make use of this dispatcher to power our back press handling. 

// What are LocalCompositions?
// In Compose, we typically pass data through the composition tree explicitly through means of
// parameters to composable functions. This is inline with the principles of unidirection 
// data flow that Compose heavily recommends using. There are situations where this won't 
// always be possible. For these cases, [LocalComposition]s can be used as an implicit way to have
// data flow through a composition.

// Another way to think about Providers is that I can get access to a value in the middle of 
// a composition, without having to pass the value in. Some other examples of Providers and 
// LocalComposiiton's are LocalContext(to get access to the context), etc.
private val LocalBackPressedDispatcher =
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
    val dispatcher = (LocalBackPressedDispatcher.current ?: return).onBackPressedDispatcher
    // remember{} is a helper composable that calculates the value passed to it only during the 
    // first composition. It then returns the same value for every subsequent composition. In the 
    // example below, it initializes the value of ComposableBackHandler() and does it only during 
    // the first composition. It's important to understand that the subsequent screens where this 
    // value is passed to are still allowed to modify the value (depending on whether it has 
    // mutable properties).  
    val handler = remember { ComposableBackHandler(enabled) }
    // Sometimes we need to make changes to the state of the app. For those cases, Composes provides
    // some Effect API's which provide a way to perform side effects in a predictable manner.
    // DisposableEffect is one such side effect API that provides a mechanism to perform some
    // clean up actions if the key to the effect changes or if the composable leaves composition.
    DisposableEffect(dispatcher) {
        dispatcher.addCallback(handler)
        // The onDispose block inside the onCommit effect is called to do any clean up(if 
        // necessary) for the side effect that executed inside onCommit. 
        onDispose { handler.remove() }
    }
    // LaunchedEffect is another Effect API that allows you to safely call suspect functions from
    // within the Compose scope. It takes in a key and if LaunchedEffect is recomposed with a
    // different key, the existing coroutine will be cancelled and the new suspend function will be
    // launched in a new coroutine.
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
    // LocalContext is a LocalComposition for accessting the context value that we are used to using
    // in Android.

    // LocalComposition is an implicit way to pass values down the compose tree. Typically, we pass values
    // down the compose tree by passing them as parameters. This makes it easy to have fairly
    // modular and reusable components that are easy to test as well. However, for certain types
    // of data where multiple components need to use it, it makes sense to have an implicit way
    // to access this data. For such scenarios, we use LocalComposition. In this example, we use the
    // LocalContext to get hold of the Context object. In order to get access to the latest
    // value of the LocalComposition, use the "current" property eg - LocalContext.current. Some other
    // examples of common LocalComposition's are LocalTextInputService,LocalDensity, etc.
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
        LocalBackPressedDispatcher provides context as ComponentActivity
    ) {
        handler {
            onBackPressed()
        }
    }
}
