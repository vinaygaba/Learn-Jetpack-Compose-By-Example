package com.example.jetpackcompose.core

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackcompose.R
import com.example.jetpackcompose.animation.Animation1Activity
import com.example.jetpackcompose.animation.Animation2Activity
import com.example.jetpackcompose.customview.CustomViewActivity
import com.example.jetpackcompose.customview.CustomViewPainActivity
import com.example.jetpackcompose.image.ImageActivity
import com.example.jetpackcompose.layout.ConstraintLayoutActivity
import com.example.jetpackcompose.layout.LayoutModifierActivity
import com.example.jetpackcompose.scrollers.GridLayoutActivity
import com.example.jetpackcompose.scrollers.HorizontalScrollableActivity
import com.example.jetpackcompose.scrollers.VerticalScrollableActivity
import com.example.jetpackcompose.stack.StackActivity
import com.example.jetpackcompose.state.StateActivity
import com.example.jetpackcompose.text.TextFieldActivity
import com.example.jetpackcompose.text.CustomTextActivity
import com.example.jetpackcompose.text.SimpleTextActivity
import com.example.jetpackcompose.layout.ViewLayoutConfigurationsActivity
import com.example.jetpackcompose.material.AlertDialogActivity
import com.example.jetpackcompose.material.BottomNavigationActivity
import com.example.jetpackcompose.material.ButtonActivity
import com.example.jetpackcompose.material.DrawerAppActivity
import com.example.jetpackcompose.material.FixedActionButtonActivity
import com.example.jetpackcompose.material.MaterialActivity
import com.example.jetpackcompose.state.ProcessDeathActivity
import com.example.jetpackcompose.theme.DarkModeActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startSimpleTextExample(@Suppress("UNUSED_PARAMETER")view: View) {
        startActivity(Intent(this, SimpleTextActivity::class.java))
    }

    fun startCustomTextExample(@Suppress("UNUSED_PARAMETER")view: View) {
        startActivity(Intent(this, CustomTextActivity::class.java))
    }

    fun startVerticalScrollableExample(@Suppress("UNUSED_PARAMETER")view: View) {
        startActivity(Intent(this, VerticalScrollableActivity::class.java))
    }

    fun startHorizontalScrollableExample(@Suppress("UNUSED_PARAMETER")view: View) {
        startActivity(Intent(this, HorizontalScrollableActivity::class.java))
    }

    fun starLoadImageExample(@Suppress("UNUSED_PARAMETER")view: View) {
        startActivity(Intent(this, ImageActivity::class.java))
    }

    fun startGridExample(@Suppress("UNUSED_PARAMETER")view: View) {
        startActivity(Intent(this, GridLayoutActivity::class.java))
    }

    fun startAlertDialogExample(@Suppress("UNUSED_PARAMETER")view: View) {
        startActivity(Intent(this, AlertDialogActivity::class.java))
    }

    fun startDrawerExample(@Suppress("UNUSED_PARAMETER")view: View) {
        startActivity(Intent(this, DrawerAppActivity::class.java))
    }

    fun startButtonsExample(@Suppress("UNUSED_PARAMETER")view: View) {
        startActivity(Intent(this, ButtonActivity::class.java))
    }

    fun startStateExample(@Suppress("UNUSED_PARAMETER")view: View) {
        startActivity(Intent(this, StateActivity::class.java))
    }

    fun startCustomViewExample(@Suppress("UNUSED_PARAMETER")view: View) {
        startActivity(Intent(this, CustomViewActivity::class.java))
    }

    fun startCustomViewPaintExample(@Suppress("UNUSED_PARAMETER")view: View) {
        startActivity(Intent(this, CustomViewPainActivity::class.java))
    }

    fun startAutofillTextExample(@Suppress("UNUSED_PARAMETER")view: View) {
        startActivity(Intent(this, TextFieldActivity::class.java))
    }

    fun startStackExample(@Suppress("UNUSED_PARAMETER")view: View) {
        startActivity(Intent(this, StackActivity::class.java))
    }

    fun startViewAlignExample(@Suppress("UNUSED_PARAMETER")view: View) {
        startActivity(Intent(this, ViewLayoutConfigurationsActivity::class.java))
    }

    fun startMaterialDesignExample(@Suppress("UNUSED_PARAMETER")view: View) {
        startActivity(Intent(this, MaterialActivity::class.java))
    }

    fun startFixedActionButtonExample(@Suppress("UNUSED_PARAMETER")view: View) {
        startActivity(Intent(this, FixedActionButtonActivity::class.java))
    }

    fun startConstraintLayoutExample(@Suppress("UNUSED_PARAMETER")view: View) {
        startActivity(Intent(this, ConstraintLayoutActivity::class.java))
    }

    fun startBottomNavigationExample(@Suppress("UNUSED_PARAMETER")view: View) {
        startActivity(Intent(this, BottomNavigationActivity::class.java))
    }

    fun startAnimation1Example(@Suppress("UNUSED_PARAMETER")view: View) {
        startActivity(Intent(this, Animation1Activity::class.java))
    }

    fun startAnimation2Example(@Suppress("UNUSED_PARAMETER")view: View) {
        startActivity(Intent(this, Animation2Activity::class.java))
    }

    fun startThemeExample(@Suppress("UNUSED_PARAMETER")view: View) {
        startActivity(Intent(this, DarkModeActivity::class.java))
    }

    fun startLayoutModifierExample(@Suppress("UNUSED_PARAMETER")view: View) {
        startActivity(Intent(this, LayoutModifierActivity::class.java))
    }

    fun startProcessDeathExample(@Suppress("UNUSED_PARAMETER")view: View) {
        startActivity(Intent(this, ProcessDeathActivity::class.java))
    }
}
