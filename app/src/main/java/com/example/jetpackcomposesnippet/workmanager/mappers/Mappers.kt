package com.example.jetpackcomposesnippet.workmanager.mappers

import com.example.jetpackcomposesnippet.workmanager.data.model.QuoteDTO

fun QuoteDTO.toDomain(workType: String): QuoteDTO {
    return QuoteDTO(author, id, quote, workType)
}