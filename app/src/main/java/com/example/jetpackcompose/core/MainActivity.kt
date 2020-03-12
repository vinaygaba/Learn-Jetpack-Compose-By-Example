package com.example.jetpackcompose.core

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackcompose.R
import com.example.jetpackcompose.animation.AnimationActivity
import com.example.jetpackcompose.customview.CustomViewActivity
import com.example.jetpackcompose.customview.CustomViewPainActivity
import com.example.jetpackcompose.image.ImageActivity
import com.example.jetpackcompose.layout.ConstraintLayoutActivity
import com.example.jetpackcompose.material.*
import com.example.jetpackcompose.scrollers.GridLayoutActivity
import com.example.jetpackcompose.scrollers.HorizontalScrollableActivity
import com.example.jetpackcompose.scrollers.VerticalScrollableActivity
import com.example.jetpackcompose.stack.StackActivity
import com.example.jetpackcompose.state.StateActivity
import com.example.jetpackcompose.text.TextFieldActivity
import com.example.jetpackcompose.text.CustomTextActivity
import com.example.jetpackcompose.text.SimpleTextActivity
import com.example.jetpackcompose.layout.ViewLayoutConfigurationsActivity
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

    fun startGridExample(view: View) {
        startActivity(Intent(this, GridLayoutActivity::class.java))
    }

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
        startActivity(Intent(this, CustomViewPainActivity::class.java))
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

    fun startAnimationExample(view: View) {
        startActivity(Intent(this, AnimationActivity::class.java))
    }

    fun startThemeExample(view: View) {
        startActivity(Intent(this, DarkModeActivity::class.java))
    }
}
