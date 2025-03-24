package com.tutorials.mywishlishapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "wish-title")
    val title: String = "",
    @ColumnInfo(name = "wish-desc")
    val description: String = "",
)

object DummyWish {
    val wishlist = listOf(
        Wish(title = "Google Watch 2", description = "An android watch"),
        Wish(title = "Google Pixel 7", description = "An android phone"),
        Wish(title = "Google Pixel 6", description = "An android phone"),
        Wish(title = "Bean Bag", description = "A cotton bag for the body")
    )
}