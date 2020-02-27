# Jetpack Compose Playground

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

|Example|Preview|
|-------|-------|
|[1. How do I display "Hello World" on the screen using Jetpack Compose?](https://github.com/vinaygaba/Jetpack-Compose-Playground/blob/master/app/src/main/java/com/example/jetpackcomposeplayground/text/SimpleTextActivity.kt)| |
|[2. How do I style & customize my text?](https://github.com/vinaygaba/Jetpack-Compose-Playground/blob/master/app/src/main/java/com/example/jetpackcomposeplayground/text/CustomTextActivity.kt)| |
|[3. How do I add a Button to my screen?](https://github.com/vinaygaba/Jetpack-Compose-Playground/blob/master/app/src/main/java/com/example/jetpackcomposeplayground/button/ButtonActivity.kt)||
|[4. How do I make a view clickable and do actions when clicked?](https://github.com/vinaygaba/Jetpack-Compose-Playground/blob/master/app/src/main/java/com/example/jetpackcomposeplayground/dialogs/AlertDialogActivity.kt#L36)| |
|[5. How do I display an alert dialog/popup modal?](https://github.com/vinaygaba/Jetpack-Compose-Playground/blob/master/app/src/main/java/com/example/jetpackcomposeplayground/dialogs/AlertDialogActivity.kt)| |
|[6. How do I display an image in Jetpack Compose?](https://github.com/vinaygaba/Jetpack-Compose-Playground/blob/master/app/src/main/java/com/example/jetpackcomposeplayground/image/ImageActivity.kt) <br> [6a. How to load an image from the resource folder?](https://github.com/vinaygaba/Jetpack-Compose-Playground/blob/master/app/src/main/java/com/example/jetpackcomposeplayground/image/ImageActivity.kt#L61)<br>[6b. How to make an image view with rounded corners?](https://github.com/vinaygaba/Jetpack-Compose-Playground/blob/master/app/src/main/java/com/example/jetpackcomposeplayground/image/ImageActivity.kt#L73)<br>[6c. How to load an image over the network using Picasso?](https://github.com/vinaygaba/Jetpack-Compose-Playground/blob/master/app/src/main/java/com/example/jetpackcomposeplayground/image/ImageActivity.kt#L85) <br> [6d. How to load an image over the network using Glide?](https://github.com/vinaygaba/Jetpack-Compose-Playground/blob/master/app/src/main/java/com/example/jetpackcomposeplayground/image/ImageActivity.kt#L135)| |
|[7. How do I add a drawer to my screen?](https://github.com/vinaygaba/Jetpack-Compose-Playground/blob/master/app/src/main/java/com/example/jetpackcomposeplayground/drawers/DrawerAppActivity.kt)| |
|[8. How do I draw using a canvas?](https://github.com/vinaygaba/Jetpack-Compose-Playground/blob/master/app/src/main/java/com/example/jetpackcomposeplayground/customview/CustomViewActivity.kt)| |
|[9. How do I detect touch events in a custom view?](https://github.com/vinaygaba/Jetpack-Compose-Playground/blob/master/app/src/main/java/com/example/jetpackcomposeplayground/customview/CustomViewPainActivity.kt)| |
|[10. How do I display a list in my app?](https://github.com/vinaygaba/Jetpack-Compose-Playground/blob/master/app/src/main/java/com/example/jetpackcomposeplayground/scrollers/VerticalScrollableActivity.kt) <br>[10a. Is there a RecyclerView equivalent?](https://github.com/vinaygaba/Jetpack-Compose-Playground/blob/master/app/src/main/java/com/example/jetpackcomposeplayground/scrollers/VerticalScrollableActivity.kt#L40) <br> [10b. How can I have a grid layout?](https://github.com/vinaygaba/Jetpack-Compose-Playground/blob/master/app/src/main/java/com/example/jetpackcomposeplayground/scrollers/GridLayoutActivity.kt) <br> [10.c How can I build a horizontally scrollable carousel?](https://github.com/vinaygaba/Jetpack-Compose-Playground/blob/master/app/src/main/java/com/example/jetpackcomposeplayground/scrollers/HorizontalScrollableActivity.kt)| |
|[11. What's the EditText equivalent in Jetpack Compose?](https://github.com/vinaygaba/Jetpack-Compose-Playground/blob/master/app/src/main/java/com/example/jetpackcomposeplayground/text/TextFieldActivity.kt) <br>[11a. How can I use a custom text style for a text input?](https://github.com/vinaygaba/Jetpack-Compose-Playground/blob/master/app/src/main/java/com/example/jetpackcomposeplayground/text/TextFieldActivity.kt#L71) <br>[11b. How can I change the keyboard type to accept numbers?](https://github.com/vinaygaba/Jetpack-Compose-Playground/blob/master/app/src/main/java/com/example/jetpackcomposeplayground/text/TextFieldActivity.kt#L90) <br>[11c. How can I create a Search bar?](https://github.com/vinaygaba/Jetpack-Compose-Playground/blob/master/app/src/main/java/com/example/jetpackcomposeplayground/text/TextFieldActivity.kt#L104) <br>[11d. How can I apply a visual transformation to an input text?](https://github.com/vinaygaba/Jetpack-Compose-Playground/blob/master/app/src/main/java/com/example/jetpackcomposeplayground/text/TextFieldActivity.kt#L123)| |
| | |
| | |
| | |

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