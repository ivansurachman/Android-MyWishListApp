package com.tutorials.mywishlishapp.data

import kotlinx.coroutines.flow.Flow

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