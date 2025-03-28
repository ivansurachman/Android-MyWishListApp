package com.tutorials.mywishlishapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tutorials.mywishlishapp.ui.theme.MyWishlishAppTheme

/**
 * MainActivity is the primary entry point for the MyWishlist application.
 * It sets up the application's theme, content, and navigation.
 *
 * This class extends ComponentActivity, providing a base for building activities
 * with Compose UI. It leverages the Material 3 Scaffold for a basic layout structure
 * and enables edge-to-edge display.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyWishlishAppTheme {
                Scaffold (
                    modifier = Modifier.fillMaxSize(),
                ) {innerPadding ->
                    Navigation(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyWishlishAppTheme {
        Navigation()
    }
}