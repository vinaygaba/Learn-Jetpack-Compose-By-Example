package com.example.jetpackcompose.image

import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.ui.graphics.ImageAsset
import androidx.ui.graphics.ImageAssetConfig
import androidx.ui.graphics.asAndroidBitmap
import androidx.ui.graphics.colorspace.ColorSpace
import androidx.ui.graphics.colorspace.ColorSpaces

// Copied from the Compose source code. This class is internal for the time being but the Compose
// team has promised that this won't be the case for very long.
class AndroidImageAsset(private val bitmap: Bitmap) : ImageAsset {

    override val width: Int
        get() = bitmap.width

    override val height: Int
        get() = bitmap.height

    override val config: ImageAssetConfig
        get() = bitmap.config.toImageConfig()

    override val colorSpace: ColorSpace
        get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            bitmap.colorSpace?.toComposeColorSpace() ?: ColorSpaces.Srgb
        } else {
            ColorSpaces.Srgb
        }

    override fun readPixels(
        buffer: IntArray,
        startX: Int,
        startY: Int,
        width: Int,
        height: Int,
        bufferOffset: Int,
        stride: Int
    ) {
        // Internal Android implementation that copies the pixels from the underlying
        // android.graphics.Bitmap if the configuration supports it
        val androidBitmap = asAndroidBitmap()
        var recycleTarget = false
        val targetBitmap =
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O ||
                androidBitmap.config != Bitmap.Config.HARDWARE
            ) {
                androidBitmap
            } else {
                // Because we are creating a copy for the purposes of reading pixels out of it
                // be sure to recycle this temporary bitmap when we are finished with it.
                recycleTarget = true

                // Pixels of a hardware bitmap cannot be queried directly so make a copy
                // of it into a configuration that can be queried
                // Passing in false for the isMutable parameter as we only intend to read pixel
                // information from the bitmap
                androidBitmap.copy(Bitmap.Config.ARGB_8888, false)
            }

        targetBitmap.getPixels(
            buffer,
            bufferOffset,
            stride,
            startX,
            startY,
            width,
            height
        )
        // Recycle the target if we are done with it
        if (recycleTarget) {
            targetBitmap.recycle()
        }
    }

    override val hasAlpha: Boolean
        get() = bitmap.hasAlpha()

    override fun prepareToDraw() {
        bitmap.prepareToDraw()
    }
}

internal fun Bitmap.Config.toImageConfig(): ImageAssetConfig {
    // Cannot utilize when statements with enums that may have different sets of supported
    // values between the compiled SDK and the platform version of the device.
    // As a workaround use if/else statements
    // See https://youtrack.jetbrains.com/issue/KT-30473 for details
    @Suppress("DEPRECATION")
    return if (this == Bitmap.Config.ALPHA_8) {
        ImageAssetConfig.Alpha8
    } else if (this == Bitmap.Config.RGB_565) {
        ImageAssetConfig.Rgb565
    } else if (this == Bitmap.Config.ARGB_4444) {
        ImageAssetConfig.Argb8888 // Always upgrade to Argb_8888
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && this == Bitmap.Config.RGBA_F16) {
        ImageAssetConfig.F16
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && this == Bitmap.Config.HARDWARE) {
        ImageAssetConfig.Gpu
    } else {
        ImageAssetConfig.Argb8888
    }
}

@RequiresApi(Build.VERSION_CODES.O)
internal fun android.graphics.ColorSpace.toComposeColorSpace(): ColorSpace {
    return when (this) {
        android.graphics.ColorSpace.get(android.graphics.ColorSpace.Named.SRGB)
        -> ColorSpaces.Srgb
        android.graphics.ColorSpace.get(android.graphics.ColorSpace.Named.ACES)
        -> ColorSpaces.Aces
        android.graphics.ColorSpace.get(android.graphics.ColorSpace.Named.ACESCG)
        -> ColorSpaces.Acescg
        android.graphics.ColorSpace.get(android.graphics.ColorSpace.Named.ADOBE_RGB)
        -> ColorSpaces.AdobeRgb
        android.graphics.ColorSpace.get(android.graphics.ColorSpace.Named.BT2020)
        -> ColorSpaces.Bt2020
        android.graphics.ColorSpace.get(android.graphics.ColorSpace.Named.BT709)
        -> ColorSpaces.Bt709
        android.graphics.ColorSpace.get(android.graphics.ColorSpace.Named.CIE_LAB)
        -> ColorSpaces.CieLab
        android.graphics.ColorSpace.get(android.graphics.ColorSpace.Named.CIE_XYZ)
        -> ColorSpaces.CieXyz
        android.graphics.ColorSpace.get(android.graphics.ColorSpace.Named.DCI_P3)
        -> ColorSpaces.DciP3
        android.graphics.ColorSpace.get(android.graphics.ColorSpace.Named.DISPLAY_P3)
        -> ColorSpaces.DisplayP3
        android.graphics.ColorSpace.get(android.graphics.ColorSpace.Named.EXTENDED_SRGB)
        -> ColorSpaces.ExtendedSrgb
        android.graphics.ColorSpace.get(android.graphics.ColorSpace.Named.LINEAR_EXTENDED_SRGB)
        -> ColorSpaces.LinearExtendedSrgb
        android.graphics.ColorSpace.get(android.graphics.ColorSpace.Named.LINEAR_SRGB)
        -> ColorSpaces.LinearSrgb
        android.graphics.ColorSpace.get(android.graphics.ColorSpace.Named.NTSC_1953)
        -> ColorSpaces.Ntsc1953
        android.graphics.ColorSpace.get(android.graphics.ColorSpace.Named.PRO_PHOTO_RGB)
        -> ColorSpaces.ProPhotoRgb
        android.graphics.ColorSpace.get(android.graphics.ColorSpace.Named.SMPTE_C)
        -> ColorSpaces.SmpteC
        else -> ColorSpaces.Srgb
    }
}
