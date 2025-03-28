package com.tutorials.mywishlishapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


/**
 * Navigation Composable function to handle navigation within the application.
 *
 * This function sets up the navigation graph using Jetpack Compose Navigation,
 * defining the different screens and how to navigate between them. It uses a
 * [NavHostController] to manage the back stack and transitions.
 *
 * @param viewModel The [WishViewModel] instance used by the composables within the navigation graph.
 *                  Defaults to a newly created ViewModel if not provided.
 * @param navController The [NavHostController] responsible for managing the navigation.
 *                      Defaults to a [rememberNavController] instance if not provided.
 * @param modifier Modifier to be applied to the root composable.
 *
 * The Navigation graph includes the following screens:
 *   - **HomeScreen**: The main screen displaying a list of wishes.
 *       - Route: [Screen.HomeScreen.route]
 *       - Composable: [HomeView]
 *   - **AddScreen**:  Screen for adding or editing a wish.
 *       - Route: [Screen.AddScreen.route] + "/{id}"
 *       - Arguments:
 *         - `id`: A Long representing the ID of the wish to edit. Defaults to 0L (meaning a new wish).
 *       - Composable: [AddEditDetailView]
 *
 * The flow is:
 *   - The navigation starts at the [Screen.HomeScreen.route].
 *   - From the home screen, you can navigate to the AddScreen, providing an id for edit or 0 for adding new wish.
 *
 * @see NavHostController
 * @see NavHost
 * @see Screen
 * @see HomeView
 * @see AddEditDetailView
 * @see WishViewModel
 */
@Composable
fun Navigation(
    viewModel: WishViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ){
        composable(Screen.HomeScreen.route){
            HomeView(
                modifier= modifier,
                navController = navController,
                viewModel = viewModel
            )
        }

        composable(
            route = Screen.AddScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.LongType
                    defaultValue = 0L
                    nullable = false
                }
            )
        ) {
            val id = it.arguments?.getLong("id") ?: 0L
            AddEditDetailView(
                modifier = modifier,
                id = id,
                viewModel = viewModel,
                navController = navController
            )
        }
    }
}