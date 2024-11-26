package com.learningroots.currency.conversion.feature_currency_conversion.domain.repository.local

import com.learningroots.currency.conversion.feature_currency_conversion.domain.model.CurrencyWithRate
import com.learningroots.currency.conversion.feature_currency_conversion.domain.model.Rate
import kotlinx.coroutines.flow.Flow

/**
 * RateRepository - Repository that have abstract methods for database operations.
 *
 * @author Santosh Yadav
 * @version 1.0
 * @since 2024-10-6
 */

interface RateRepository {

    fun getRates(): Flow<List<Rate>>

    fun getCurrencyWithRates(): Flow<List<CurrencyWithRate>>

    suspend fun insertRates(rate: Rate)

    suspend fun insertCurrency(currency: List<CurrencyWithRate>)
}