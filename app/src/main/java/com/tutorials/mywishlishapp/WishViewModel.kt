package com.tutorials.mywishlishapp

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tutorials.mywishlishapp.data.Wish
import com.tutorials.mywishlishapp.data.WishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


/**
 * [WishViewModel] is a ViewModel class responsible for managing the UI-related data and
 * state for the Wish screen or feature. It interacts with the [WishRepository] to perform
 * CRUD operations on wishes.
 *
 * @property wishRepository The repository responsible for handling wish data. Defaults to the
 *                          wishRepository provided by the [Graph].
 */
class WishViewModel(
    private val wishRepository: WishRepository = Graph.wishRepository
): ViewModel() {
    var wishTitleState by mutableStateOf("")
    var wishDescriptionState by mutableStateOf("")

    fun onWishTitleChange(newTitle: String){
        wishTitleState = newTitle
    }

    fun onWishDescriptionChange(newDescription: String){
        wishDescriptionState = newDescription
    }

    // lateinit = late initialized,
    // dimana variable akan diinisialisasi nanti sebelum digunakan
    lateinit var getAllWishes: Flow<List<Wish>>

    init {
        viewModelScope.launch {
            getAllWishes = wishRepository.getAllWishes()
        }
    }

    // Dispactchers.IO digunakan untuk menjalankan operasi input/output
    // untuk optimasi performa aplikasi
    fun addWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.addWish(wish)
        }
    }

    fun getWishById(id: Long): Flow<Wish> {
        return wishRepository.getWishById(id)
    }

    fun updateWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.updateWish(wish)
        }
    }

    fun deleteWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.deleteWish(wish)
        }
    }
}