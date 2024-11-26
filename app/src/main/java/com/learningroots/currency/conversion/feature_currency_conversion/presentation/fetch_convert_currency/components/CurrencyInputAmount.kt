package com.learningroots.currency.conversion.feature_currency_conversion.presentation.fetch_convert_currency.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.learningroots.currency.conversion.feature_currency_conversion.domain.model.CurrencyWithRate
import com.learningroots.currency.conversion.feature_currency_conversion.presentation.fetch_convert_currency.CurrencyViewModel
import com.learningroots.currency.conversion.ui.theme.Blue40
import com.learningroots.currency.conversion.ui.theme.Dark600

/**
 * CurrencyInputAmount - Component for capturing numeric input from the user.
 *
 * @author Santosh Yadav
 * @version 1.0
 * @since 2024-10-8
 */

@Composable
fun CurrencyInputAmount(viewmodel: CurrencyViewModel, currencyRateList: List<CurrencyWithRate>) {
    var text by remember {
        mutableStateOf("1.0")
    }
    var isValidDouble by remember {
        mutableStateOf(true)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top=15.dp, start = 25.dp, end = 25.dp)

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(end=5.dp)
                .border(1.dp, color = Dark600, RoundedCornerShape(5.dp))
                .background(Blue40, RoundedCornerShape(5.dp))
                .weight(1f)
        ) {
            BasicTextField(
                value = text,
                onValueChange = { input ->
                    if (input.isEmpty() || input.toDoubleOrNull() != null) {
                        text = input
                        isValidDouble = true
                        if (text.isNotEmpty())
                            viewmodel.setCurrencyAmount(text.toDouble())
                        else
                            viewmodel.setCurrencyAmount(0.0)
                        viewmodel.calculateCurrencyConversion()
                    } else {
                        isValidDouble = false
                    }
                },
                modifier = Modifier
                    .padding(15.dp),
                singleLine = true,
                maxLines = 1,
                textStyle = TextStyle(
                    color = Dark600
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                cursorBrush = SolidColor(Dark600)
            )
        }
        DropDown(currencyRateList, viewmodel)
    }
}