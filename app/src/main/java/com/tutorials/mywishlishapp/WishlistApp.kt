package com.tutorials.mywishlishapp

import android.app.Application

/**
 * WishlistApp is the main Application class for the Wishlist app.
 *
 * It extends the Android Application class and serves as the entry point for the application's
 * lifecycle.  It's responsible for initializing global components and resources needed by the app.
 *
 * Key responsibilities:
 *   - Providing the application context to dependency injection.
 *   - Initializing the dependency graph (using the `Graph` class).
 *
 * Usage:
 *   This class is automatically instantiated by the Android system when the application starts.
 *   It should be defined in the AndroidManifest.xml file under the `<application>` tag's
 *   `android:name` attribute.
 *   e.g. <application ... android:name=".WishlistApp">
 */
class WishlistApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}