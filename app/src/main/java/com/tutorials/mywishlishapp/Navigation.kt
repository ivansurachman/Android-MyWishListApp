package com.tutorials.mywishlishapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


import androidx.navigation.compose.rememberNavController


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

        composable(Screen.AddScreen.route) {
            AddEditDetailView(
                modifier = modifier,
                id = 0L,
                viewModel = viewModel,
                navController = navController
            )
        }
    }
}