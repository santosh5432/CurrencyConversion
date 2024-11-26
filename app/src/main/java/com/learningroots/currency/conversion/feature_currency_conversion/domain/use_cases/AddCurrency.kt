package com.learningroots.currency.conversion.feature_currency_conversion.domain.use_cases

import com.learningroots.currency.conversion.feature_currency_conversion.domain.model.CurrencyWithRate
import com.learningroots.currency.conversion.feature_currency_conversion.domain.repository.local.RateRepository

/**
 * Author: Santosh Yadav
 * Created on: 23-11-2024 15:45
 */
class AddCurrency(
    val rateRepository: RateRepository
) {
    suspend operator fun invoke(currency: List<CurrencyWithRate>) {
        rateRepository.insertCurrency(currency)
    }
}