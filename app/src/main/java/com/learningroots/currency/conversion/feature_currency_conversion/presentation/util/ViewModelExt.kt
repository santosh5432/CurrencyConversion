package com.learningroots.currency.conversion.feature_currency_conversion.presentation.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learningroots.currency.conversion.util.EventBus
import kotlinx.coroutines.launch

/**
 * ViewModel.sendEvent - Viewmodel extension function for event handling.
 *
 * @author Santosh Yadav
 * @version 1.0
 * @since 2024-10-6
 */

fun ViewModel.sendEvent(event: Any){
    viewModelScope.launch {
        EventBus.sendEvent(event)
    }
}