# Learn Jetpack Compose By Example ![Android CI](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/workflows/Android%20CI/badge.svg)

![Feature Image](screenshots/jetpack_compose_poster.png)

Over the course of the last few years Android development has gone
through significant changes in how we structure our apps, the language
we use for development, the tooling & libraries that help us speed up
our development and the improvements in testing our apps. What had not
changed in all these years is the Android UI toolkit. This changes with
Jetpack Compose that aims to re-imagine what Android UI development
would look like using declarative programming principles. It is heavily
influenced by existing web and mobile frameworks such as React, Litho,
Vue & Flutter and would be a paradigm shift in Android UI development as
we know it.

This repository aims to show the Jetpack Compose way of building common
Android UI that we are accustomed to building.

Examples
-----------------

Each example is meant to be self contained and tries to explain all the
concepts that its using with comments. Compose also comes with this
nifty feature that lets you preview each component in the IntelliJ IDE
itself. To do so, go to any of the examples examples and the click on
the preview button in the top right corner. This is possible if your
`@Composable` component has a corresponding `@Preview` method associated
with it. Look at the examples below for all this to make a bit more
sense.

![Jetpack Compose Preview Functionality](screenshots/compose_preview.gif)

### General

|Example|Preview|
|-------|-------|
|[How do I display text on the screen using Jetpack Compose?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/text/SimpleTextActivity.kt)|<img src ="screenshots/simple_text.png" width=214 height=400> |
|[How do I make a view clickable and do actions when clicked?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/material/AlertDialogActivity.kt#L50)| <img src ="screenshots/alert_dialog.gif" width=214 height=400> |
|[How do I add padding around a view?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/layout/LayoutModifierActivity.kt#L51) <br/>  [How do I add an offset to a layout?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/layout/LayoutModifierActivity.kt#L90) <br/> [How do I enforce a layout to have a fixed aspect ratio?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/layout/LayoutModifierActivity.kt#L112)|<img src ="screenshots/layout_modifiers.png" width=214 height=400>|
|How do I add a background color to any section of the screen? <br/> [Example 1](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/text/CustomTextActivity.kt#L187) <br/> [Example 2](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/material/DrawerAppActivity.kt#L127)|<img src ="screenshots/background_surface1.png" width=214> <br/> <img src ="screenshots/background_surface2.png" width=214> |
|[How do I get FrameLayout like functionality to stack views on top of one another?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/stack/StackActivity.kt)| |
|How do I do animation in Jetpack Compose? <br/>[Example: Rotation Animation](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/animation/Animation1Activity.kt) <br/> [Example: Color Change Animation](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/animation/Animation2Activity.kt) |<img src ="screenshots/animation_rotation.gif" width=214 height=400> <br/> <br/> <img src ="screenshots/color_animation.gif" width=214 height=400>|
|[How do styles & themes work in Jetpack Compose?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/theme/DarkModeActivity.kt) <br/> [How do I add dark mode capability to my app?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/theme/DarkModeActivity.kt)|<img src ="screenshots/dark_mode.gif" width=214 height=400> |

### Material Design

|Example|Preview|
|-------|-------|
|[How do I add a Material Design Card?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/material/MaterialActivity.kt#L113) <br> [How do I display a linear progress bar?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/material/MaterialActivity.kt#L271) <br> [What about a circular progress bar?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/material/MaterialActivity.kt#L305) <br> [How do I add a Snackbar?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/material/MaterialActivity.kt#L323) <br> [How to add a Material Design Slider?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/material/MaterialActivity.kt#L392) <br> [How to configure a two/three state checkbox?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/material/MaterialActivity.kt#L195) <br> [How do I add a radio button group?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/material/MaterialActivity.kt#L238)|<img src ="screenshots/material_design_components.gif" width=214 height=400> |
|[How do I add a Floating Action Button to my screen?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/material/FixedActionButtonActivity.kt) |<img src ="screenshots/fab_bottom_nav.gif" width=214 height=400> |
|[How do I create a Bottom App Bar?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/material/BottomNavigationActivity.kt)|<img src ="screenshots/bottom_navigation_components.gif" width=214 height=400> |
|[How do I add a drawer to my screen?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/material/DrawerAppActivity.kt)|<img src ="screenshots/drawer_layout.gif" width=214 height=400>|
|[How do I add Ripple effect to a view?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/material/MaterialActivity.kt#L474) | <img src ="screenshots/ripple.gif" width=214> |
|[How do I add a Button to my screen?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/material/ButtonActivity.kt)|<img src ="screenshots/buttons.png" width=214 height=400>|
|[How do I display an alert dialog/popup modal?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/material/AlertDialogActivity.kt)|<img src ="screenshots/alert_dialog.gif" width=214 height=400>|


### Text

|Example|Preview|
|-------|-------|
|[How do I style & customize my text?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/text/CustomTextActivity.kt)| <img src ="screenshots/text_styles.gif" width=214 height=400> |
|[How do you take text input from a user in Jetpack Compose?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/text/TextFieldActivity.kt#L74) <br/> [How can I use a custom text style for a text input?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/text/TextFieldActivity.kt#L108) <br/> [How can I change the keyboard type to accept numbers?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/text/TextFieldActivity.kt#L151) <br/> [How can I create a Search bar?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/text/TextFieldActivity.kt#L188) <br/> [How can I apply a visual transformation to an input text?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/text/TextFieldActivity.kt#L241)|<img src ="screenshots/text_input.gif" width=214 height=400> |

### Images

|Example|Preview|
|-------|-------|
|[How do I display an image in Jetpack Compose?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/image/ImageActivity.kt) <br/> [How to load an image from the resource folder?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/image/ImageActivity.kt#L85) <br/> [How to make an image view with rounded corners?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/image/ImageActivity.kt#L123) <br/> [How to load an image over the network using Picasso?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/image/ImageActivity.kt#L155) <br/> [How to load an image over the network using Glide?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/image/ImageActivity.kt#L215)|<img src ="screenshots/load_images.gif" width=214 height=400>|

### Lists

|Example|Preview|
|-------|-------|
|[How do I display a list in my app?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/scrollers/VerticalScrollableActivity.kt)<br/> [Is there a RecyclerView equivalent?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/scrollers/VerticalScrollableActivity.kt#L48)|<img src ="screenshots/vertical_list.gif" width=214 height=400>|
|[How can I have a grid layout?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/scrollers/GridLayoutActivity.kt)|<img src ="screenshots/grid_layout.gif" width=214 height=400> |
|[How can I build a horizontally scrollable carousel?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/scrollers/HorizontalScrollableActivity.kt)| <img src ="screenshots/horizontal_list.gif" width=214 height=400>|


### Flexible Layouts
|Example|Preview|
|-------|-------|
|[How do I align the baseline of two views?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/layout/ViewLayoutConfigurationsActivity.kt#L286) <br/><br/> **What's the layout weight equivalent in Jetpack Compose?**<br>[Example with equal weights](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/layout/ViewLayoutConfigurationsActivity.kt#L78) <br>[Example with unequal weights](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/layout/ViewLayoutConfigurationsActivity.kt#L110)<br/><br/> **How do I auto arrange my views similar to FlexBox?**<br>[Add space between views](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/layout/ViewLayoutConfigurationsActivity.kt#L143)<br>[Evenly distribute space](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/layout/ViewLayoutConfigurationsActivity.kt#L172)<br>[Add space around the views](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/layout/ViewLayoutConfigurationsActivity.kt#L201)<br>[Tightly packed centering of views](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/layout/ViewLayoutConfigurationsActivity.kt#L230)|<img src ="screenshots/view_layout_arrangements.gif" width=214 height=400> |
|[How do I use constraint layouts?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/layout/ConstraintLayoutActivity.kt) <br/> [How do I use guidelines with constraint layouts?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/layout/ConstraintLayoutActivity.kt#L161) <br/> [How do I add barriers when using constraint layouts?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/layout/ConstraintLayoutActivity.kt#L230) <br/> [How do I add bias when using constraint layouts?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/layout/ConstraintLayoutActivity.kt#L315)|<img src ="screenshots/constraints.png" width=214 height=400>|

### Custom Views

|Example|Preview|
|-------|-------|
|[How do I draw using a canvas?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/customview/CustomViewActivity.kt)|<img src ="screenshots/custom_view.png" width=214 height=400>|
|[How do I detect touch events in a custom view?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/customview/CustomViewPainActivity.kt)| |


### State Management
|Example|Preview|
|-------|-------|
|[How do I store state information in Jetpack Compose?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/state/StateActivity.kt)|<img src ="screenshots/state_example.gif" width=214 height=400> |
|[How do I retain state across process death & activity recreation(like orientation changes)?](https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/state/ProcessDeathActivity.kt) | <img src ="screenshots/process_death.gif" width=214 height=400> |


Credits
-----------------
Author: Vinay Gaba (vinaygaba@gmail.com)

<a href="https://twitter.com/vinaygaba">
  <img alt="Follow me on Twitter"
       src="https://github.com/gabrielemariotti/cardslib/raw/master/demo/images/twitter64.png" />
</a>
<a href="https://www.linkedin.com/in/vinaygaba">
  <img alt="Follow me on LinkedIn"
       src="https://github.com/gabrielemariotti/cardslib/raw/master/demo/images/linkedin.png" />
</a>


License
-----------------

    Copyright 2020 Vinay Gaba

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
