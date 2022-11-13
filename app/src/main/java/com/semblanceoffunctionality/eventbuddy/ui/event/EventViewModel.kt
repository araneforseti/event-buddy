package com.semblanceoffunctionality.eventbuddy.ui.event

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.semblanceoffunctionality.eventbuddy.data.Event
import com.semblanceoffunctionality.eventbuddy.data.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject internal constructor(
    private val savedStateHandle: SavedStateHandle,
    private val eventRepository: EventRepository
) : ViewModel() {
    fun updateEvent(event: Event) {
        CoroutineScope(Dispatchers.IO).launch {
            eventRepository.addEvent(event)
        }
    }

    var eventName: String = savedStateHandle.get<String>(EVENT_ID_SAVED_STATE_KEY)!!
    val event = eventRepository.getEvent(eventName).asLiveData()

    companion object {
        private const val EVENT_ID_SAVED_STATE_KEY = "event"
    }
}