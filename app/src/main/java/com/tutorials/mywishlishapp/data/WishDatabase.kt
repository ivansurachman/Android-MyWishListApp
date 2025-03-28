package com.tutorials.mywishlishapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * WishDatabase
 *
 * This abstract class represents the Room database for storing and managing Wish entities.
 * It serves as the central access point for all data related to wishes in the application.
 *
 * @property wishDao Provides an interface for interacting with the Wish data in the database.
 *
 * Annotations:
 *   @Database: Specifies that this class defines a Room database.
 *     - entities: An array of classes representing the entities that make up the database.
 *                 In this case, it includes the [Wish] entity.
 *     - version: The version number of the database. Increment this when the database schema changes.
 *                The current version is 1.
 *     - exportSchema: A boolean indicating whether to export the database schema into a folder.
 *                    Set to `false` here to disable schema export.
 */
@Database(
    entities = [Wish::class],
    version = 1,
    exportSchema = false
)
abstract class WishDatabase: RoomDatabase() {
    abstract fun wishDao(): WishDao
}