package com.example.jetpackcomposeplayground.image

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.onCommit
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.ui.core.*
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Image
import androidx.ui.layout.*
import androidx.ui.material.surface.Card
import androidx.ui.res.loadImageResource
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontFamily
import androidx.ui.text.font.FontWeight
import androidx.ui.tooling.preview.Preview
import com.example.jetpackcomposeplayground.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

class ImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VerticalScroller {
                Column(modifier = ExpandedHeight wraps Spacing(16.dp)) {
                    DisplayImagesComponent()
                }
            }
        }
    }
}

@Composable
fun DisplayImagesComponent() {
    TitleComponent("Load image from the resource folder")
    LocalResourceImageComponent(R.drawable.lenna)

    TitleComponent("Load image from url using Picasso")
    NetworkImageComponentPicasso(url = "https://github.com/vinaygaba/CreditCardView/raw/master/images/Feature%20Image.png")

    TitleComponent("Image with rounded corners")
    ImageWithRoundedCorners(R.drawable.lenna)

    // Look at NetworkImageComponentGlide below for an example with Glide.
}

@Composable
fun LocalResourceImageComponent(@DrawableRes resId: Int) {
    // There are multiple methods available to load an image resource in Compose. However, it would
    // be advisable to use the loadImageResource method as it loads the image asynchronously
    val image = +loadImageResource(resId)
    image.resource.resource?.let {
        Container(modifier = Height(200.dp) wraps ExpandedWidth) {
            DrawImage(image = it)
        }
    }
}

@Composable
fun ImageWithRoundedCorners(@DrawableRes resId: Int) {
    // There are multiple methods available to load an image resource in Compose. However, it would
    // be advisable to use the loadImageResource method as it loads the image asynchronously
    val image = +loadImageResource(resId)
    image.resource.resource?.let {
        Card(shape = RoundedCornerShape(8.dp), modifier = Height(200.dp) wraps ExpandedWidth) {
            DrawImage(image = it)
        }
    }
}

@Composable
fun NetworkImageComponentPicasso(url: String) {
    // Source code inspired from - https://kotlinlang.slack.com/archives/CJLTWPH7S/p1573002081371500.
    // Made some minor changes to the code Leland posted.
    var image by +state<Image?> { null }
    var drawable by +state<Drawable?> { null }
    +onCommit(url) {
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
                image = bitmap?.let { AndroidImage(it) }
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

    Container(modifier = Height(200.dp) wraps ExpandedWidth) {
        val theImage = image
        val theDrawable = drawable
        if (theImage != null) {
            DrawImage(image = theImage)
        } else if (theDrawable != null) {
            Draw { canvas, parentSize -> theDrawable.draw(canvas.nativeCanvas) }
        }
    }
}

/**
 * There is an ongoing issue with kapt and compose since it uses the IR backend. This causes the
 * app to not even compile - https://issuetracker.google.com/issues/143232368. For that reason,
 * I am commenting this for now and removing the dependency on glide, but once that issue is
 * resolved, the below code should ideally work with Glide.
 */
//@Composable
//fun NetworkImageComponentGlide(url: String) {
//    var image by +state<Image?> { null }
//    var drawable by +state<Drawable?> { null }
//    +onCommit(url) {
//        val context = +ambient(ContextAmbient)
//        val glide = Glide.with(context)
//        val target = object : CustomTarget<Bitmap>() {
//            override fun onLoadCleared(placeholder: Drawable?) {
//                image = null
//                drawable = placeholder
//            }
//
//            override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap>?) {
//                image = AndroidImage(bitmap)
//            }
//        }
//        glide
//            .asBitmap()
//            .load(url)
//            .into(target)
//
//        onDispose {
//            image = null
//            drawable = null
//            glide.clear(target)
//        }
//    }
//
//    Container(modifier = Height(200.dp) wraps ExpandedWidth) {
//        val theImage = image
//        val theDrawable = drawable
//        if (theImage != null) {
//            DrawImage(image = theImage)
//        } else if (theDrawable != null) {
//            Draw { canvas, parentSize -> theDrawable.draw(canvas.nativeCanvas) }
//        }
//    }
//}

@Composable
fun TitleComponent(title: String) {
    Text(title, style = TextStyle(fontFamily = FontFamily.Monospace, fontWeight = FontWeight.W900,
        fontSize = 14.sp), modifier = Spacing(16.dp))
}

@Preview
@Composable
fun DisplayImagesComponentPreview() {
    DisplayImagesComponent()
}
