package com.example.jetpackcomposesnippet.workmanager.data.remote

import com.example.jetpackcomposesnippet.workmanager.data.model.QuoteDTO
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    //https://dummyjson.com/quotes/random

    @GET("quotes/random")
    suspend fun getQuotes(): Response<QuoteDTO>?
}