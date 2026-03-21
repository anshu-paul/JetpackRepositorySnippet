package com.example.jetpackcomposesnippet.presentation.coin_detail

import com.example.jetpackcomposesnippet.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
