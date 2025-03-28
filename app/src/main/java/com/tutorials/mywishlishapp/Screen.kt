package com.tutorials.mywishlishapp

/**
 * Sealed class representing the different screens in the application.
 * Each screen is defined as an object extending this class and is associated with a unique route string.
 * This class is used for navigation and to define the possible destinations within the app.
 *
 * @property route The unique string identifier for this screen's route. Used for navigation.
 */
sealed class Screen (val route: String) {
    object HomeScreen : Screen("home_screen")
    object AddScreen : Screen("add_screen")
}