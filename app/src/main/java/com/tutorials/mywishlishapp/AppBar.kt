package com.tutorials.mywishlishapp

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp

/**
 * A custom Composable function that creates an AppBar (TopAppBar) with a title and optional back navigation.
 *
 * This function provides a consistent AppBar design across the application. It includes the following features:
 * - Displays a title.
 * - Optionally shows a back navigation icon (arrow) if the title is not "My Wishlist".
 * - Customizable back navigation behavior via a lambda.
 * - Consistent styling (color, elevation).
 *
 * @param title The title to be displayed in the AppBar.
 * @param onBackNavClicked A lambda function that is executed when the back navigation icon is clicked.
 *                         Defaults to an empty lambda (no action). It's only triggered when the back button is visible.
 *
 * Example Usage:
 * ```
 * AppBarView(
 *     title = "Product Details",
 *     onBackNavClicked = { navController.navigateUp() }
 * )
 * ```
 *
 * In this example "Product details" will be displayed as title and the back arrow will be displayed.
 * When the user clicks on the back arrow, the navigation will go back to the previous route.
 *
 * ```
 * AppBarView(
 *     title = "My Wishlist",
 * )
 * ```
 *
 * In this example "My Wishlist" will be displayed as title and no back arrow will be displayed.
 */
@Composable
fun AppBarView(
    title: String,
    onBackNavClicked: () -> Unit ={},
){
    val navigationIcon: (@Composable () -> Unit)? =
        if (!title.contains("My Wishlist")){
            {
                IconButton(onClick = { onBackNavClicked() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        tint = Color.White,
                        contentDescription = null
                    )
                }
            }
        }else{
            null
        }



    TopAppBar(
        title = {
            Text(
                text = title,
                color = colorResource(id = R.color.white),
                modifier = Modifier
                    .padding(start = 4.dp)
                    .heightIn(max = 24.dp)
            )
        },
        elevation = 3.dp,
        backgroundColor = colorResource(id = R.color.app_bar_color),
        navigationIcon = navigationIcon
    )
}