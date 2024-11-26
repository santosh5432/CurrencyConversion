package com.learningroots.currency.conversion.feature_currency_conversion.presentation.fetch_convert_currency.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.learningroots.currency.conversion.feature_currency_conversion.domain.model.CurrencyWithRate
import com.learningroots.currency.conversion.ui.theme.Blue40
import com.learningroots.currency.conversion.ui.theme.Dark600
import com.learningroots.currency.conversion.util.roundToTwoDecimalPlaces

/**
 * CurrencyRatesGrid - Component to show all the currency conversions in grid.
 *
 * @author Santosh Yadav
 * @version 1.0
 * @since 2024-10-8
 */

@Composable
fun CurrencyRatesGrid(currencyRates: List<CurrencyWithRate>) {
    LazyVerticalGrid(
        modifier = Modifier
            .padding(top = 5.dp)
            .fillMaxHeight(),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.SpaceEvenly,
        contentPadding = PaddingValues(15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        content = {
            items(currencyRates.size) { index ->
                Card(
                    colors = CardDefaults.cardColors(Blue40),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .height(80.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "${currencyRates[index].rate.roundToTwoDecimalPlaces()}, ${currencyRates[index].currencyCode}",
                            maxLines = 1,
                            color = Dark600
                        )
                        Text(
                            text = currencyRates[index].currency,
                            modifier = Modifier.padding(top = 5.dp),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = Dark600
                        )
                    }
                }
            }
        }
    )
}