package com.learningroots.currency.conversion.feature_currency_conversion.presentation.fetch_convert_currency.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.learningroots.currency.conversion.feature_currency_conversion.domain.model.CurrencyWithRate
import com.learningroots.currency.conversion.feature_currency_conversion.presentation.fetch_convert_currency.CurrencyViewModel
import com.learningroots.currency.conversion.ui.theme.Blue40
import com.learningroots.currency.conversion.ui.theme.Dark600

/**
 * DropDown - Component to show currency dropdown menu.
 *
 * @author Santosh Yadav
 * @version 1.0
 * @since 2024-10-8
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDown(
    items: List<CurrencyWithRate>,
    viewmodel: CurrencyViewModel
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedItem by remember {
        mutableStateOf("USD")
    }
    ExposedDropdownMenuBox(
        modifier = Modifier
            .width(150.dp)
            .height(50.dp)
            .border(1.dp, color = Dark600, RoundedCornerShape(5.dp))
            .clip(RoundedCornerShape(5.dp)),
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            modifier = Modifier.menuAnchor(
                type = MenuAnchorType.PrimaryNotEditable,
                enabled = true
            ),
            value = selectedItem,
            onValueChange = { },
            readOnly = true,
            colors = TextFieldDefaults.colors(
                disabledContainerColor = Blue40,
                unfocusedContainerColor = Blue40,
                focusedContainerColor = Blue40,
                focusedTextColor = Dark600,
                unfocusedTextColor = Dark600

            ),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            }
        )
        ExposedDropdownMenu(expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
            containerColor= Blue40
        ) {
            items.forEachIndexed { index, rate ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = rate.currencyCode,
                            color = Dark600
                        )
                    },
                    onClick = {
                        selectedItem = items[index].currencyCode
                        expanded = false
                        viewmodel.setSelectedCurrency(selectedItem)
                        viewmodel.calculateCurrencyConversion()
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }

}