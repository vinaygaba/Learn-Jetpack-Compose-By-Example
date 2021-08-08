package com.example.jetpackcompose.image

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.jetpackcompose.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target


class ImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is an extension function of Activity that sets the @Composable function that's
        // passed to it as the root view of the activity. This is meant to replace the .xml file
        // that we would typically set using the setContent(R.id.xml_file) method. The setContent
        // block defines the activity's layout.
        setContent {
            // LazyColumn is a vertically scrolling list that only composes and lays out the currently
            // visible items. This is very similar to what RecyclerView tries to do as well.
            LazyColumn(modifier = Modifier.padding(16.dp)) {
                displayImagesComponent()
            }
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
fun LazyListScope.displayImagesComponent() {
    // item is a DSL available in the LazyColumn scope. This allows you to render a composable
    // for a single element in the list
    item {
        TitleComponent("Load image from the resource folder")
        LocalResourceImageComponent(R.drawable.landscape)
    }

    item {
        TitleComponent("Load image from url using Picasso")
        NetworkImageComponentPicasso(
            url = "https://github.com/vinaygaba/CreditCardView/raw/master/images/Feature%20Image.png"
        )
    }

    item {
        TitleComponent("Load image from url using Glide")
        NetworkImageComponentGlide(
            url = "https://github.com/vinaygaba/CreditCardView/raw/master/images/Feature%20Image.png"
        )
    }

    item {
        TitleComponent("Image with rounded corners")
        ImageWithRoundedCorners(R.drawable.landscape)
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun LocalResourceImageComponent(@DrawableRes resId: Int) {
    // There are multiple methods available to load an image resource in Compose. However, it would
    // be advisable to use the painterResource method as it loads an image resource asynchronously
    val image = painterResource(resId)

    // Image is a pre-defined composable that lays out and draws a given [ImageBitmap].

    // You can think of Modifiers as implementations of the decorators pattern that are
    // used to modify the composable that its applied to. In this example, we configure the
    // Image composable to have a height of 200 dp.
    Image(
        painter = image,
        contentDescription = null,
        modifier = Modifier
            .sizeIn(maxHeight = 200.dp)
            .fillMaxWidth()
    )
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun ImageWithRoundedCorners(@DrawableRes resId: Int) {
    // There are multiple methods available to load an image resource in Compose. However, it would
    // be advisable to use the painterResource method as it loads an image resource asynchronously
    val image = painterResource(resId)
    // Column is a composable that places its children in a vertical sequence. You
    // can think of it similar to a LinearLayout with the vertical orientation.
    // In addition we also pass a few modifiers to it.

    // You can think of Modifiers as implementations of the decorators pattern that are
    // used to modify the composable that its applied to. In this example, we configure the
    // Box composable to clip the corners of the image.
    Column(
        modifier = Modifier.clip(RoundedCornerShape(8.dp))
    ) {
        // Image is a pre-defined composable that lays out and draws a given [ImageBitmap].
        Image(
            painter = image,
            modifier = Modifier.height(200.dp),
            contentDescription = null
        )
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun NetworkImageComponentPicasso(
    url: String,
    modifier: Modifier = Modifier
) {
    // Source code inspired from - https://kotlinlang.slack.com/archives/CJLTWPH7S/p1573002081371500.
    // Made some minor changes to the code Leland posted.
    val sizeModifier = modifier
        .fillMaxWidth()
        .sizeIn(maxHeight = 200.dp)
    var image by remember { mutableStateOf<ImageBitmap?>(null) }
    var drawable by remember { mutableStateOf<Drawable?>(null) }
    // Sometimes we need to make changes to the state of the app. For those cases, Composes provides
    // some Effect API's which provide a way to perform side effects in a predictable manner.
    // DisposableEffect is one such side effect API that provides a mechanism to perform some
    // clean up actions if the key to the effect changes or if the composable leaves composition.
    DisposableEffect(url) {
        val picasso = Picasso.get()
        val target = object : Target {
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                // TODO(lmr): we could use the drawable below
                drawable = placeHolderDrawable
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                drawable = errorDrawable
            }

            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                image = bitmap?.asImageBitmap()
            }
        }
        picasso
            .load(url)
            .into(target)
        onDispose {
            image = null
            drawable = null
            picasso.cancelRequest(target)
        }
    }

    val theImage = image
    val theDrawable = drawable
    if (theImage != null) {
        // Column is a composable that places its children in a vertical sequence. You
        // can think of it similar to a LinearLayout with the vertical orientation. 
        // In addition we also pass a few modifiers to it.

        // You can think of Modifiers as implementations of the decorators pattern that are
        // used to modify the composable that its applied to. 
        Column(
            modifier = sizeModifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Image is a pre-defined composable that lays out and draws a given [ImageBitmap].
            Image(bitmap = theImage, contentDescription = null)
        }
    } else if (theDrawable != null) {
        // We use the Canvas composable that gives you access to a canvas that you can draw
        // into. We also pass it a modifier.
        Canvas(modifier = sizeModifier) {
            drawIntoCanvas { canvas ->
                theDrawable.draw(canvas.nativeCanvas)
            }
        }
    }
}

/**
 * There is an ongoing issue with kapt and compose since it uses the IR backend. This causes the
 * app to not even compile if you are using kapt- https://issuetracker.google.com/issues/143232368.
 * For that reason, I am avoiding a dependency on the glide annotation processor. If you add that
 * dependency(or any other kapt related dependency for that matter), the app won't even compile.
 */
@Composable
fun NetworkImageComponentGlide(
    url: String, modifier: Modifier = Modifier
) {
    // Reacting to state changes is the core behavior of Compose. You will notice a couple new
    // keywords that are compose related - remember & mutableStateOf.remember{} is a helper
    // composable that calculates the value passed to it only during the first composition. It then
    // returns the same value for every subsequent composition. Next, you can think of
    // mutableStateOf as an observable value where updates to this variable will redraw all
    // the composable functions that access it. We don't need to explicitly subscribe at all. Any
    // composable that reads its value will be recomposed any time the value
    // changes. This ensures that only the composables that depend on this will be redraw while the
    // rest remain unchanged. This ensures efficiency and is a performance optimization. It
    // is inspired from existing frameworks like React.
    var image by remember { mutableStateOf<ImageBitmap?>(null) }
    var drawable by remember { mutableStateOf<Drawable?>(null) }
    val sizeModifier = modifier
        .fillMaxWidth()
        .sizeIn(maxHeight = 200.dp)

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
    val context = LocalContext.current
    // Sometimes we need to make changes to the state of the app. For those cases, Composes provides
    // some Effect API's which provide a way to perform side effects in a predictable manner.
    // DisposableEffect is one such side effect API that provides a mechanism to perform some
    // clean up actions if the key to the effect changes or if the composable leaves composition.
    DisposableEffect(url) {
        val glide = Glide.with(context)
        val target = object : CustomTarget<Bitmap>() {
            override fun onLoadCleared(placeholder: Drawable?) {
                image = null
                drawable = placeholder
            }

            override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap>?) {
                image = bitmap.asImageBitmap()
            }
        }
        glide
            .asBitmap()
            .load(url)
            .into(target)

        onDispose {
            image = null
            drawable = null
            glide.clear(target)
        }
    }

    val theImage = image
    val theDrawable = drawable
    if (theImage != null) {
        // Column is a composable that places its children in a vertical sequence. You
        // can think of it similar to a LinearLayout with the vertical orientation. 
        // In addition we also pass a few modifiers to it.

        // You can think of Modifiers as implementations of the decorators pattern that are
        // used to modify the composable that its applied to.
        Column(
            modifier = sizeModifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Image is a pre-defined composable that lays out and draws a given [ImageBitmap].
            Image(bitmap = theImage, contentDescription = null)
        }
    } else if (theDrawable != null) {
        // We use the Canvas composable that gives you access to a canvas that you can draw
        // into. We also pass it a modifier.
        Canvas(modifier = sizeModifier) {
            drawIntoCanvas { canvas ->
                theDrawable.draw(canvas.nativeCanvas)
            }
        }
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun TitleComponent(title: String) {
    // Text is a predefined composable that does exactly what you'd expect it to - display text on
    // the screen. It allows you to customize its appearance using style, fontWeight, fontSize, etc.
    Text(
        title, style = TextStyle(
            fontFamily = FontFamily.Monospace, fontWeight = FontWeight.W900,
            fontSize = 14.sp, color = Color.Black
        ), modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    )
}

// RoundedCornerClipModifier is a custom Modifier that is responsible for clipping and
// providing a rounder corner to the composable its applied to.
// Recommendation from -
// https://kotlinlang.slack .com/archives/CJLTWPH7S/p1589826323481600?thread_ts=1589821110.478100&cid=CJLTWPH7S
// TODO(vinaygaba) Add some more comments here
fun Modifier.RoundedCornerClipModifier(size: Dp): Modifier = composed {
    val shape = RoundedCornerShape(size)
    clip(shape)
}

/**
 * Android Studio lets you preview your composable functions within the IDE itself, instead of
 * needing to download the app to an Android device or emulator. This is a fantastic feature as you
 * can preview all your custom components(read composable functions) from the comforts of the IDE.
 * The main restriction is, the composable function must not take any parameters. If your composable
 * function requires a parameter, you can simply wrap your component inside another composable
 * function that doesn't take any parameters and call your composable function with the appropriate
 * params. Also, don't forget to annotate it with @Preview & @Composable annotations.
 */
@Preview("Load image stored in local resources folder")
@Composable
fun LocalResourceImageComponentPreview() {
    Column {
        LocalResourceImageComponent(R.drawable.landscape)
    }
}

@Preview("Load an image over the network using the Picasso library")
@Composable
fun NetworkImageComponentPicassoPreview() {
    NetworkImageComponentPicasso("https://github.com/vinaygaba/CreditCardView/raw/master/images/Feature%20Image.png")
}

@Preview("Load an image over the network using the Glide library")
@Composable
fun NetworkImageComponentGlidePreview() {
    NetworkImageComponentGlide("https://github.com/vinaygaba/CreditCardView/raw/master/images/Feature%20Image.png")
}

@Preview("Add round corners to an image")
@Composable
fun ImageWithRoundedCornersPreview() {
    ImageWithRoundedCorners(R.drawable.landscape)
}
