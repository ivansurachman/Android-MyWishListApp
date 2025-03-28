package com.tutorials.mywishlishapp.data

import kotlinx.coroutines.flow.Flow

/**
 * Repository class for managing wish data.
 *
 * This class acts as an intermediary between the data source (WishDao) and the
 * higher layers of the application (e.g., ViewModels). It provides a clean
 * API for interacting with wish data, abstracting away the details of the
 * underlying data source.
 *
 * @property wishDao The Data Access Object (DAO) responsible for interacting with
 *                   the wish data in the database.
 */
class WishRepository (private val wishDao: WishDao) {

    suspend fun addWish(wishEntity: Wish) {
        wishDao.addWish(wishEntity)
    }

    fun getAllWishes(): Flow<List<Wish>> = wishDao.getAllWishes()

    fun getWishById(id: Long): Flow<Wish> {
        return wishDao.getWishById(id)
    }

    suspend fun updateWish(wishEntity: Wish) {
        wishDao.updateWish(wishEntity)
    }

    suspend fun deleteWish(wishEntity: Wish) {
        wishDao.deleteWish(wishEntity)
    }
}