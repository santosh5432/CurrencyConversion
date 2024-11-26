package com.learningroots.currency.conversion.util

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

/**
 * EventBus - provides implementation for events.
 *
 * @author Santosh Yadav
 * @version 1.0
 * @since 2024-10-8
 */

object EventBus {
    private val _events = Channel<Any>()
    val events = _events.receiveAsFlow()

    suspend fun sendEvent(event: Any) {
        _events.send(event)
    }
}


sealed interface Event{
    data class Toast(val message: String): Event
}