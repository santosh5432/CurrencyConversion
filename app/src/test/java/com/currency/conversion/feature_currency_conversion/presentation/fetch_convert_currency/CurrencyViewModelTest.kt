package com.learningroots.currency.conversion.feature_currency_conversion.presentation.fetch_convert_currency

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import arrow.core.Either
import com.learningroots.currency.conversion.feature_currency_conversion.data.remote.mapper.toNetworkError
import com.learningroots.currency.conversion.feature_currency_conversion.domain.model.CurrencyRates
import com.learningroots.currency.conversion.feature_currency_conversion.domain.model.Rate
import com.learningroots.currency.conversion.feature_currency_conversion.domain.repository.remote.CurrencyRepository
import com.learningroots.currency.conversion.feature_currency_conversion.domain.use_cases.CurrencyUseCases
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

/**
 * @author Santosh Yadav
 * @version 1.0
 * @since 2024-10-12
 */

@ExperimentalCoroutinesApi
class CurrencyViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var currencyRepository: CurrencyRepository
    private lateinit var currencyUseCases: CurrencyUseCases
    private lateinit var viewModel: CurrencyViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        currencyRepository = mock(CurrencyRepository::class.java)
        currencyUseCases = mock(CurrencyUseCases::class.java)
        viewModel = CurrencyViewModel(currencyRepository, currencyUseCases)
    }

    @Test
    fun currencyViewmodel_getCurrencyRatesFromApi_expectedSuccessResponse() = runTest {
        val mockRates = CurrencyRates(
            rates = mapOf("USD" to 1.0, "INR" to 75.0),
            base = "USD"
        )
        `when`(currencyRepository.getCurrencyRates()).thenReturn(Either.catch {
            mockRates
        }.mapLeft {
            it.toNetworkError()
        }
        )

        viewModel.getCurrencyRatesFromApi()
        viewModel.state.test {
            val state = awaitItem()
            Assert.assertEquals(mockRates, state.currencyRates)
            Assert.assertFalse(state.isLoading)
        }
    }

    @Test
    fun currencyViewmodel_getCurrencyRatesFromApi_expectedFailureResponse() = runTest {
        val error = Throwable("API Error")
        `when`(currencyRepository.getCurrencyRates()).thenReturn(
            Either.catch {
                CurrencyRates(
                    rates = mapOf("USD" to 1.0, "INR" to 75.0),
                    base = "USD"
                )
            }.mapLeft {
                error.toNetworkError()
            }
        )

        viewModel.getCurrencyRatesFromApi()
        viewModel.state.test {
            val state = awaitItem()
            Assert.assertEquals("API Error", state.error)
            Assert.assertFalse(state.isLoading)
        }
    }

    @Test
    fun currencyViewmodel_calculateCurrencyConversion_expectedValidCurrencyConversions() = runTest {
        val mockRates = listOf(
            Rate("USD", 1.0),
            Rate("INR", 75.0),
            Rate("EUR", 0.85)
        )
        `when`(currencyUseCases.getCurrency()).thenReturn(flowOf(mockRates))

        viewModel.setCurrencyAmount(100.0)
        viewModel.setSelectedCurrency("USD")
        viewModel.calculateCurrencyConversion()
        viewModel.state.test {
            val state = awaitItem()
            val expectedRates = listOf(
                Rate("USD", 100.0),
                Rate("INR", 7500.0),
                Rate("EUR", 85.0)
            )
            Assert.assertEquals(expectedRates, state.rateList)
        }
    }
}

