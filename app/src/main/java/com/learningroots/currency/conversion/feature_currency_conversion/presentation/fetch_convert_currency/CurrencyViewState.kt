package com.learningroots.currency.conversion.feature_currency_conversion.presentation.fetch_convert_currency

import com.learningroots.currency.conversion.feature_currency_conversion.domain.model.CurrencyRates
import com.learningroots.currency.conversion.feature_currency_conversion.domain.model.CurrencyResponse
import com.learningroots.currency.conversion.feature_currency_conversion.domain.model.CurrencyWithRate
import com.learningroots.currency.conversion.feature_currency_conversion.domain.model.Rate

/**
 * CurrencyViewState - state holder model class for all required view states.
 *
 * @author Santosh Yadav
 * @version 1.0
 * @since 2024-10-8
 */

data class CurrencyViewState(
    val isLoading:Boolean = false,
    val currencyRates: CurrencyRates? = null,
    val currency: CurrencyResponse? = null,
    val error: String? = null,
    val rateList: List<Rate> = emptyList(),
    val currencyList: List<CurrencyWithRate> = emptyList()
)
