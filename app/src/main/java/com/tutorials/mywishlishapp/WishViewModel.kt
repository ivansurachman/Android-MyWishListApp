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


class WishViewModel(
    private val wishRepository: WishRepository
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

    fun addWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.addWish(wish)
        }
    }
}