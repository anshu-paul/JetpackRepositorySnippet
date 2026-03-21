package com.example.jetpackcomposesnippet.domain.repository

import com.example.jetpackcomposesnippet.data.remote.dto.CoinDetailsDto
import com.example.jetpackcomposesnippet.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>
    suspend fun getCoinById(coinId: String): CoinDetailsDto
}