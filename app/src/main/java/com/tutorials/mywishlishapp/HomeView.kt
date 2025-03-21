package com.tutorials.mywishlishapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeView(modifier: Modifier = Modifier){
    Scaffold( modifier = modifier,
        topBar = {AppBarView(title = "My Wishlist")}
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(it)){

        }
    }
}