package com.learningroots.currency.conversion.feature_currency_conversion.presentation.fetch_convert_currency


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.learningroots.currency.conversion.feature_currency_conversion.domain.model.CurrencyWithRate
import com.learningroots.currency.conversion.feature_currency_conversion.presentation.fetch_convert_currency.components.CurrencyInputAmount
import com.learningroots.currency.conversion.feature_currency_conversion.presentation.fetch_convert_currency.components.CurrencyRatesGrid
import com.learningroots.currency.conversion.feature_currency_conversion.presentation.fetch_convert_currency.components.LoadingDialog
import com.learningroots.currency.conversion.feature_currency_conversion.presentation.fetch_convert_currency.components.TopBar
import com.learningroots.currency.conversion.ui.theme.Blue40
import com.learningroots.currency.conversion.ui.theme.GradientBottom
import com.learningroots.currency.conversion.ui.theme.GradientTop

/**
 * CurrencyConversionScreen - Main screen with currency conversion features.
 *
 * @author Santosh Yadav
 * @version 1.0
 * @since 2024-10-8
 */

@Composable
fun CurrencyConversionScreen(
    viewmodel: CurrencyViewModel = hiltViewModel()
) {
    val state by viewmodel.state.collectAsStateWithLifecycle()
    CurrencyConversionContent(state = state, viewmodel = viewmodel)
}

@Composable
fun CurrencyConversionContent(
    state: CurrencyViewState,
    viewmodel: CurrencyViewModel
) {
    LoadingDialog(isLoading = state.isLoading)
    var currencyRateList by remember { mutableStateOf(emptyList<CurrencyWithRate>()) }
    currencyRateList = state.currencyList

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        topBar = {
            TopBar(title = "Currency Conversion")
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding),
            color = Blue40
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(GradientTop, GradientBottom),
                            start = Offset(0f, 0f),
                            end = Offset(0f, Float.POSITIVE_INFINITY)
                        )
                    )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    CurrencyInputAmount(viewmodel, currencyRateList)
                    CurrencyRatesGrid(currencyRateList)
                }
            }

        }
    }

}



