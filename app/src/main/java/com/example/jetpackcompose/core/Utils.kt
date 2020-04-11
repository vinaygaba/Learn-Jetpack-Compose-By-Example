package com.example.jetpackcompose.core

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.ui.graphics.Color

// Models
data class Person(
    val name: String,
    val age: Int
)

// Methods
fun getPersonList() = listOf<Person>(
    Person("Grace Hopper", 25),
    Person("Ada Lovelace", 29),
    Person("John Smith", 28),
    Person("Elon Musk", 41),
    Person("Will Smith", 31),
    Person("Robert James", 42),
    Person("Anthony Curry", 91),
    Person("Kevin Jackson", 22),
    Person("Robert Curry", 1),
    Person("John Curry", 9),
    Person("Ada Jackson", 2),
    Person("Joe Defoe", 35)
)

fun hideKeyboard(context: Context) {
    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
}

// Constants
const val LOREM_IPSUM_1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
        "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Velit ut tortor pretium " +
        "viverra suspendisse potenti nullam ac tortor. Cras semper auctor neque vitae tempus quam " +
        "pellentesque nec nam. Volutpat maecenas volutpat blandit aliquam etiam erat. Sed velit " +
        "dignissim sodales ut eu. Est ultricies integer quis auctor elit sed vulputate mi sit. " +
        "Neque volutpat ac tincidunt vitae semper quis. Sapien nec sagittis aliquam malesuada " +
        "bibendum. Etiam non quam lacus suspendisse. Viverra maecenas accumsan lacus vel facilisis. " +
        "Sit amet massa vitae tortor. Sodales ut etiam sit amet nisl purus in. Diam quam nulla " +
        "porttitor massa. Pellentesque eu tincidunt tortor aliquam nulla facilisi. " +
        "Egestas diam in arcu cursus euismod quis. Adipiscing elit duis tristique sollicitudin nibh " +
        "sit amet commodo nulla. Erat nam at lectus urna duis. Praesent elementum facilisis leo vel. " +
        "Facilisis gravida neque convallis a cras."
const val LOREM_IPSUM_2 = "Rhoncus mattis rhoncus urna neque viverra. Pharetra " +
        "convallis posuere morbi leo urna. Facilisis gravida neque convallis a. Vitae semper quis " +
        "lectus nulla at volutpat. Laoreet sit amet cursus sit amet dictum sit amet. Pellentesque " +
        "dignissim enim sit amet venenatis urna. Ipsum suspendisse ultrices gravida dictum fusce. " +
        "Lorem mollis aliquam ut porttitor. Hac habitasse platea dictumst quisque sagittis. " +
        "Sed risus ultricies tristique nulla. Mauris commodo quis imperdiet massa tincidunt nunc. " +
        "Quisque non tellus orci ac auctor."
const val LOREM_IPSUM_3 = "Sed velit dignissim sodales ut eu sem integer. Elit " +
        "scelerisque mauris pellentesque pulvinar pellentesque habitant morbi tristique. " +
        "Interdum consectetur libero id faucibus nisl tincidunt. Suspendisse sed nisi lacus sed " +
        "viverra. Egestas pretium aenean pharetra magna. Mauris vitae ultricies leo integer " +
        "malesuada nunc vel risus. In egestas erat imperdiet sed euismod nisi. Morbi tincidunt" +
        " augue interdum velit euismod in. Tempus egestas sed sed risus pretium quam vulputate " +
        "dignissim. Odio morbi quis commodo odio aenean sed. Vestibulum lectus mauris ultrices eros."

val colors = listOf(
    Color(0xFFffd7d7.toInt()),
    Color(0xFFffe9d6.toInt()),
    Color(0xFFfffbd0.toInt()),
    Color(0xFFe3ffd9.toInt()),
    Color(0xFFd0fff8.toInt())
)
