package com.learningroots.currency.conversion.feature_currency_conversion.presentation.fetch_convert_currency.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.learningroots.currency.conversion.ui.theme.Dark600
import com.learningroots.currency.conversion.ui.theme.darkBackground


/**
 * TopBar - Component to show appbar.
 *
 * @author Santosh Yadav
 * @version 1.0
 * @since 2024-10-8
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = Dark600
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.darkBackground),
        modifier = Modifier.shadow(elevation = 5.dp)
    )
}