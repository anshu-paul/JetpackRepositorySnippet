package com.example.jetpackcomposesnippet.data.repository

import com.example.jetpackcomposesnippet.data.remote.dto.CoinDetailsDto
import com.example.jetpackcomposesnippet.data.remote.dto.CoinDto
import com.example.jetpackcomposesnippet.data.remote.dto.CoinPaprikaApi
import com.example.jetpackcomposesnippet.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailsDto {
        return api.getCoinById(coinId)
    }
}