package com.tutorials.mywishlishapp

import android.content.Context
import androidx.room.Room
import com.tutorials.mywishlishapp.data.WishDatabase
import com.tutorials.mywishlishapp.data.WishRepository

/**
 * `Graph` is a singleton object responsible for providing dependencies throughout the application.
 * It acts as a simple dependency injection container.
 *
 * **Key Responsibilities:**
 *  - Holds and manages the `WishDatabase` instance.
 *  - Creates and provides the `WishRepository` instance.
 *  - Initializes the `WishDatabase` when requested.
 *
 * **Components:**
 *  - `database`: A lateinit variable that holds the Room database instance (`WishDatabase`).
 *  - `wishRepository`: A lazily initialized instance of `WishRepository`, which handles data access for wish-related operations.
 *  - `provide(context: Context)`: A function that initializes the `WishDatabase` using the provided application context.
 *
 * **Usage:**
 *  - Before accessing `wishRepository`, `provide(context)` must be called once to initialize the database.
 *  - Access `wishRepository` using `Graph.wishRepository`.
 *
 * **Benefits:**
 *  - **Dependency Management:** Centralizes the creation and provision of dependencies, simplifying access throughout the application.
 *  - **Lazy Initialization:** The `WishRepository` is only created when it's first needed, optimizing resource usage.
 *  - **Singleton Pattern:** Ensures a single instance of the database and repository, which is appropriate for most database-related operations.
 *  - **Clean Code:** Separates dependency creation from the components that use them, resulting in a cleaner and more maintainable codebase.
 */
object Graph {
    lateinit var database: WishDatabase

    // by lazy = variable akan diinisialisasi ketika dibutuhkan
    val wishRepository by lazy {
        WishRepository(
            wishDao = database.wishDao()
        )
    }

    fun provide(context: Context){
        database = Room.databaseBuilder(
            context = context,
            klass = WishDatabase::class.java,
            name = "wish-database"
        ).build()
    }
}