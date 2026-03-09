package com.example.jetpackcomposesnippet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class ContactsViewModel(name: String) : ViewModel() {

    var backgroundColour by mutableStateOf(Color.White)
        private set

    fun changeBackgroundColour() {
        backgroundColour = Color.Red
    }
}