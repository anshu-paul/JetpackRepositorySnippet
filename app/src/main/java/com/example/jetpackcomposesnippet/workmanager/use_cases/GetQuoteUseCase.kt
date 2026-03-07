package com.example.jetpackcomposesnippet.workmanager.use_cases

import com.example.jetpackcomposesnippet.workmanager.domain.repository.QuoteRepository
import javax.inject.Inject

class GetQuoteUseCase @Inject constructor(private val quoteRepository: QuoteRepository) {
     operator fun invoke() = quoteRepository.getQuotes()
}