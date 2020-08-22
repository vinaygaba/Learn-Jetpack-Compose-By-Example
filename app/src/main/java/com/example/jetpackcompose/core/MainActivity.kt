@file:Suppress("UNUSED_PARAMETER")

package com.example.jetpackcompose.core

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackcompose.R
import com.example.jetpackcompose.animation.Animation1Activity
import com.example.jetpackcompose.animation.Animation2Activity
import com.example.jetpackcompose.animation.ListAnimationActivity
import com.example.jetpackcompose.animation.TextAnimationActivity
import com.example.jetpackcompose.customview.CustomViewActivity
import com.example.jetpackcompose.customview.CustomViewPaintActivity
import com.example.jetpackcompose.customview.MeasuringScaleActivity
import com.example.jetpackcompose.customview.ZoomableActivity
import com.example.jetpackcompose.image.ImageActivity
import com.example.jetpackcompose.interop.ComposeInClassicAndroidActivity
import com.example.jetpackcompose.layout.ConstraintLayoutActivity
import com.example.jetpackcompose.layout.LayoutModifierActivity
import com.example.jetpackcompose.layout.ViewLayoutConfigurationsActivity
import com.example.jetpackcompose.material.AlertDialogActivity
import com.example.jetpackcompose.material.BottomNavigationActivity
import com.example.jetpackcompose.material.ButtonActivity
import com.example.jetpackcompose.material.DrawerAppActivity
import com.example.jetpackcompose.material.FixedActionButtonActivity
import com.example.jetpackcompose.material.FlowRowActivity
import com.example.jetpackcompose.material.MaterialActivity
import com.example.jetpackcompose.material.ShadowActivity
import com.example.jetpackcompose.scrollers.HorizontalScrollableActivity
import com.example.jetpackcompose.scrollers.VerticalScrollableActivity
import com.example.jetpackcompose.stack.StackActivity
import com.example.jetpackcompose.state.ProcessDeathActivity
import com.example.jetpackcompose.state.StateActivity
import com.example.jetpackcompose.state.backpress.BackPressActivity
import com.example.jetpackcompose.state.coroutine.CoroutineFlowActivity
import com.example.jetpackcompose.state.livedata.LiveDataActivity
import com.example.jetpackcompose.text.CustomTextActivity
import com.example.jetpackcompose.text.SimpleTextActivity
import com.example.jetpackcompose.text.TextFieldActivity
import com.example.jetpackcompose.theme.DarkModeActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startSimpleTextExample(view: View) {
        startActivity(Intent(this, SimpleTextActivity::class.java))
    }

    fun startCustomTextExample(view: View) {
        startActivity(Intent(this, CustomTextActivity::class.java))
    }

    fun startVerticalScrollableExample(view: View) {
        startActivity(Intent(this, VerticalScrollableActivity::class.java))
    }

    fun startHorizontalScrollableExample(view: View) {
        startActivity(Intent(this, HorizontalScrollableActivity::class.java))
    }

    fun starLoadImageExample(view: View) {
        startActivity(Intent(this, ImageActivity::class.java))
    }

    // The Table API has been removed in dev11 update. Removing this example until there is a 
    // better alternative available
//    fun startGridExample(view: View) {
//        startActivity(Intent(this, GridLayoutActivity::class.java))
//    }

    fun startAlertDialogExample(view: View) {
        startActivity(Intent(this, AlertDialogActivity::class.java))
    }

    fun startDrawerExample(view: View) {
        startActivity(Intent(this, DrawerAppActivity::class.java))
    }

    fun startButtonsExample(view: View) {
        startActivity(Intent(this, ButtonActivity::class.java))
    }

    fun startStateExample(view: View) {
        startActivity(Intent(this, StateActivity::class.java))
    }

    fun startCustomViewExample(view: View) {
        startActivity(Intent(this, CustomViewActivity::class.java))
    }

    fun startCustomViewPaintExample(view: View) {
        startActivity(Intent(this, CustomViewPaintActivity::class.java))
    }

    fun startAutofillTextExample(view: View) {
        startActivity(Intent(this, TextFieldActivity::class.java))
    }

    fun startStackExample(view: View) {
        startActivity(Intent(this, StackActivity::class.java))
    }

    fun startViewAlignExample(view: View) {
        startActivity(Intent(this, ViewLayoutConfigurationsActivity::class.java))
    }

    fun startMaterialDesignExample(view: View) {
        startActivity(Intent(this, MaterialActivity::class.java))
    }

    fun startFixedActionButtonExample(view: View) {
        startActivity(Intent(this, FixedActionButtonActivity::class.java))
    }

    fun startConstraintLayoutExample(view: View) {
        startActivity(Intent(this, ConstraintLayoutActivity::class.java))
    }

    fun startBottomNavigationExample(view: View) {
        startActivity(Intent(this, BottomNavigationActivity::class.java))
    }

    fun startAnimation1Example(view: View) {
        startActivity(Intent(this, Animation1Activity::class.java))
    }

    fun startAnimation2Example(view: View) {
        startActivity(Intent(this, Animation2Activity::class.java))
    }

    fun startTextInlineContentExample(view: View) {
        startActivity(Intent(this, TextAnimationActivity::class.java))
    }

    fun startListAnimation(view: View) {
        startActivity(Intent(this, ListAnimationActivity::class.java))
    }

    fun startThemeExample(view: View) {
        startActivity(Intent(this, DarkModeActivity::class.java))
    }

    fun startLayoutModifierExample(view: View) {
        startActivity(Intent(this, LayoutModifierActivity::class.java))
    }

    fun startProcessDeathExample(view: View) {
        startActivity(Intent(this, ProcessDeathActivity::class.java))
    }

    fun startLiveDataExample(view: View) {
        startActivity(Intent(this, LiveDataActivity::class.java))
    }

    fun startFlowRowExample(view: View) {
        startActivity(Intent(this, FlowRowActivity::class.java))
    }

    fun startShadowExample(view: View) {
        startActivity(Intent(this, ShadowActivity::class.java))
    }

    fun startCoroutineFlowExample(view: View) {
        startActivity(Intent(this, CoroutineFlowActivity::class.java))
    }

    fun startComposeWithClassicAndroidExample(view: View) {
        startActivity(Intent(this, ComposeInClassicAndroidActivity::class.java))
    }

    fun startMeasuringScaleExample(view: View) {
        startActivity(Intent(this, MeasuringScaleActivity::class.java))
    }

    fun startBackPressExample(view: View) {
        startActivity(Intent(this, BackPressActivity::class.java))
    }

    fun startZoomableExample(view: View) {
        startActivity(Intent(this, ZoomableActivity::class.java))
    }
}
