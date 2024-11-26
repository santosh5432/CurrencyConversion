package com.learningroots.currency.conversion.feature_currency_conversion.data.local.repository

import com.learningroots.currency.conversion.feature_currency_conversion.data.local.data_source.CurrencyDao
import com.learningroots.currency.conversion.feature_currency_conversion.domain.model.CurrencyWithRate
import com.learningroots.currency.conversion.feature_currency_conversion.domain.model.Rate
import com.learningroots.currency.conversion.feature_currency_conversion.domain.repository.local.RateRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * RateRepositoryImpl - This class is the implementation of repository responsible for db operations.
 *
 * @author Santosh Yadav
 * @version 1.0
 * @since 2024-10-5
 */

class RateRepositoryImpl @Inject constructor(
    private val currencyDao: CurrencyDao
) : RateRepository {

    override fun getRates(): Flow<List<Rate>> {
        return currencyDao.getCurrencyRates()
    }

    override fun getCurrencyWithRates(): Flow<List<CurrencyWithRate>> {
        return currencyDao.getCurrencyWithRate()
    }

    override suspend fun insertRates(rate: Rate) {
        currencyDao.insertCurrencyRates(rate)
    }

    override suspend fun insertCurrency(currency: List<CurrencyWithRate>) {
        currencyDao.insertCurrency(currency)
    }

//    override suspend fun insertCurrency(currency: Currency) {
//       currencyDao.insertCurrency(currency)
//    }

}