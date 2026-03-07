package com.example.jetpackcomposesnippet.workmanager.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jetpackcomposesnippet.workmanager.data.model.QuoteDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuote(quote: QuoteDTO)

    @Query("Select * from QuoteDTO order by time desc")
    fun getAllQuotes(): Flow<List<QuoteDTO>>
}