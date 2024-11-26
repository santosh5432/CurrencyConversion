package com.learningroots.currency.conversion.feature_currency_conversion.data.local.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.learningroots.currency.conversion.feature_currency_conversion.domain.model.CurrencyWithRate
import com.learningroots.currency.conversion.feature_currency_conversion.domain.model.Rate
import kotlinx.coroutines.flow.Flow

/**
 * CurrencyDao - This is the implementation of DAO for database operations
 *
 * @author Santosh Yadav
 * @version 1.0
 * @since 2024-10-5
 */

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM rate")
    fun getCurrencyRates(): Flow<List<Rate>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencyRates(rate: Rate)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrency(currency: List<CurrencyWithRate>)

    @Query("SELECT * from currencywithrate")
    fun getCurrencyWithRate(): Flow<List<CurrencyWithRate>>
}