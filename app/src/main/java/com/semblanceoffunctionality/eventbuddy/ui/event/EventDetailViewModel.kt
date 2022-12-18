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
class EventDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val eventRepository: EventRepository
) : ViewModel() {
    var eventName: String = savedStateHandle.get<String>(EVENT_ID_SAVED_STATE_KEY)!!
    val event = eventRepository.getEvent(eventName).asLiveData()

    fun updateEvent(event: Event) {
        CoroutineScope(Dispatchers.IO).launch {
            eventRepository.updateEvent(event)
        }
    }

    fun updateEventName(eventName: String) {
//        this.eventName = eventName
//        println("-- update name ---")
//        println(eventName)
//        println("-- end name ---")
//        this.event = eventRepository.getEvent(eventName).asLiveData()
//        println("-- update value ---")
//        println(this.event.value)
//        println("-- end value ---")
    }

    companion object {
        private const val EVENT_ID_SAVED_STATE_KEY = "event"
    }
}