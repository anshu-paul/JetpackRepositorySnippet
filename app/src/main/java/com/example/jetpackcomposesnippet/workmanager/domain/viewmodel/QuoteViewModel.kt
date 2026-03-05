package com.example.jetpackcomposesnippet.workmanager.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposesnippet.workmanager.data.model.QuoteDTO
import com.example.jetpackcomposesnippet.workmanager.use_cases.GetAllQuotesFromDbUseCase
import com.example.jetpackcomposesnippet.workmanager.use_cases.GetQuoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getAllQuotesFromDbUseCase: GetAllQuotesFromDbUseCase,
    private val getQuoteUseCase: GetQuoteUseCase
) : ViewModel() {
    //val quoteLiveData: LiveData<QuoteDTO> get() = repository.quotes

    /*init {
        viewModelScope.launch {
            repository.getQuotes()
        }
    }*/
    val uIState = getAllQuotesFromDbUseCase.invoke().map {
        UiState(it)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), UiState(emptyList()))

    fun getQuote() = getQuoteUseCase.invoke()
}

data class UiState(val dataLList: List<QuoteDTO>)