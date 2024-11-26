package com.learningroots.currency.conversion.feature_currency_conversion.presentation.fetch_convert_currency

import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learningroots.currency.conversion.feature_currency_conversion.domain.model.CurrencyRates
import com.learningroots.currency.conversion.feature_currency_conversion.domain.model.CurrencyResponse
import com.learningroots.currency.conversion.feature_currency_conversion.domain.model.CurrencyWithRate
import com.learningroots.currency.conversion.feature_currency_conversion.domain.repository.remote.CurrencyRepository
import com.learningroots.currency.conversion.feature_currency_conversion.domain.use_cases.CurrencyUseCases
import com.learningroots.currency.conversion.feature_currency_conversion.presentation.util.sendEvent
import com.learningroots.currency.conversion.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.mutableListOf

/**
 * CurrencyViewModel - Viewmodel with implementation of all required business logic for currency conversion screen.
 *
 * @author Santosh Yadav
 * @version 1.0
 * @since 2024-10-8
 */

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository,
    private val currencyUseCases: CurrencyUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(CurrencyViewState())
    val state = _state.asStateFlow()
    private val _currencyAmount = mutableDoubleStateOf(1.0)
    private var currencyAmount = _currencyAmount
    private val _selectedCurrency = mutableStateOf("USD")
    private var selectedCurrency = _selectedCurrency
    private var getCurrencyRatesJob: Job? = null
    private var actualRateList: List<CurrencyWithRate> = emptyList()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getCurrencyRatesFromApi()
        }
        viewModelScope.launch(Dispatchers.IO) {
            getRatesListFromDB()
        }
    }

    fun getCurrencyRatesFromApi() {
        val job = viewModelScope.launch(Dispatchers.IO) {
            _state.update {
                it.copy(isLoading = true)
            }
            //Get currency rates from api
            currencyRepository.getCurrencyRates()
                .onRight { currencyRates ->
                    _state.update {
                        it.copy(currencyRates = currencyRates)
                    }
                }
                .onLeft { error ->
                    _state.update {
                        it.copy(error = error.error.message)
                    }
                    sendEvent(Event.Toast(error.error.message))
                }

            //Get available currencies from api
            currencyRepository.getCurrencies()
                .onRight { currency ->
                    _state.update {
                        it.copy(currency = currency)
                    }
                }
                .onLeft { error ->
                    _state.update {
                        it.copy(error = error.error.message)
                    }
                }
            _state.update {
                it.copy(isLoading = false)
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            job.join()
            mergeSaveData(state.value.currencyRates, state.value.currency)
        }
    }

    private fun mergeSaveData(currencyRates: CurrencyRates?, currencyResponse: CurrencyResponse?) {
        viewModelScope.launch(Dispatchers.IO) {
            val currencyMap = currencyResponse?.currencies?.map { currency ->
                currency.key to currency.value
            }?.toMap() ?: emptyMap()

            val rateMap = currencyRates?.rates?.map { rate ->
                rate.key to rate.value
            }?.toMap() ?: emptyMap()

            // Merge the two maps by matching the keys
            val mergedList = currencyMap.mapNotNull { (currencyCode, currency) ->
                rateMap[currencyCode]?.let { rate ->
                    CurrencyWithRate(
                        currency = currency,
                        currencyCode = currencyCode,
                        rate = rate
                    )
                }
            }
            currencyUseCases.addCurrency(mergedList)
        }

    }

    private fun getRatesListFromDB() {
        getCurrencyRatesJob?.cancel()
        getCurrencyRatesJob = currencyUseCases.getCurrencyWithRates()
            .map { currencyRates ->
                _state.update {
                    it.copy(currencyList = currencyRates)
                }
                actualRateList = currencyRates
            }
            .launchIn(viewModelScope)
    }

    fun calculateCurrencyConversion() {
        val computedRateList = mutableListOf<CurrencyWithRate>()
        viewModelScope.launch(Dispatchers.IO) {
            val currencyRate = actualRateList.first { it.currencyCode == selectedCurrency.value }
            actualRateList.forEach {
                val computedRate = if (currencyAmount.doubleValue == 0.0) {
                    currencyAmount.doubleValue
                } else
                    (it.rate * currencyAmount.doubleValue) / currencyRate.rate
                computedRateList.add(
                    CurrencyWithRate(
                        currency = it.currency,
                        currencyCode = it.currencyCode,
                        rate = computedRate
                    )
                )
            }
            _state.update {
                it.copy(currencyList = computedRateList)
            }
        }
    }

    fun setCurrencyAmount(amount: Double) {
        _currencyAmount.doubleValue = amount
    }

    fun setSelectedCurrency(currency: String) {
        _selectedCurrency.value = currency
    }

}