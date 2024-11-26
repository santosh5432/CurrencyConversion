package com.learningroots.currency.conversion.feature_currency_conversion.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.learningroots.currency.conversion.feature_currency_conversion.presentation.fetch_convert_currency.CurrencyConversionScreen
import com.learningroots.currency.conversion.ui.theme.Blue40
import com.learningroots.currency.conversion.ui.theme.CurrencyConversionTheme
import com.learningroots.currency.conversion.util.Event
import com.learningroots.currency.conversion.util.EventBus
import dagger.hilt.android.AndroidEntryPoint

/**
 * MainActivity - This is the main entry point of the app.
 *
 * @author Santosh Yadav
 * @version 1.0
 * @since 2024-10-4
 */

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CurrencyConversionTheme {
                val lifecycle = LocalLifecycleOwner.current.lifecycle
                LaunchedEffect(key1 = lifecycle){
                    repeatOnLifecycle(state = Lifecycle.State.STARTED){
                        EventBus.events.collect{ event ->
                            if(event is Event.Toast){
                                Toast.makeText(this@MainActivity, event.message,Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
              Surface (modifier = Modifier.fillMaxSize(),
                  color = Blue40){
                  CurrencyConversionScreen()
              }
            }
        }
    }
}

