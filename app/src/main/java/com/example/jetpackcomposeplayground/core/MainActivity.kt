package com.example.jetpackcomposeplayground.core

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackcomposeplayground.dialogs.AlertDialogActivity
import com.example.jetpackcomposeplayground.drawers.DrawerAppActivity
import com.example.jetpackcomposeplayground.R
import com.example.jetpackcomposeplayground.button.ButtonActivity
import com.example.jetpackcomposeplayground.image.ImageActivity
import com.example.jetpackcomposeplayground.scrollers.GridLayoutActivity
import com.example.jetpackcomposeplayground.scrollers.HorizontalScrollableActivity
import com.example.jetpackcomposeplayground.scrollers.VerticalScrollableActivity
import com.example.jetpackcomposeplayground.state.StateActivity
import com.example.jetpackcomposeplayground.text.CustomTextActivity
import com.example.jetpackcomposeplayground.text.SimpleTextActivity

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
}
