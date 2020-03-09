package com.example.jetpackcomposeplayground.core

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackcomposeplayground.R
import com.example.jetpackcomposeplayground.animation.AnimationActivity
import com.example.jetpackcomposeplayground.customview.CustomViewActivity
import com.example.jetpackcomposeplayground.customview.CustomViewPainActivity
import com.example.jetpackcomposeplayground.image.ImageActivity
import com.example.jetpackcomposeplayground.layout.ConstraintLayoutActivity
import com.example.jetpackcomposeplayground.material.*
import com.example.jetpackcomposeplayground.scrollers.GridLayoutActivity
import com.example.jetpackcomposeplayground.scrollers.HorizontalScrollableActivity
import com.example.jetpackcomposeplayground.scrollers.VerticalScrollableActivity
import com.example.jetpackcomposeplayground.stack.StackActivity
import com.example.jetpackcomposeplayground.state.StateActivity
import com.example.jetpackcomposeplayground.text.TextFieldActivity
import com.example.jetpackcomposeplayground.text.CustomTextActivity
import com.example.jetpackcomposeplayground.text.SimpleTextActivity
import com.example.jetpackcomposeplayground.layout.ViewLayoutConfigurationsActivity

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
}
