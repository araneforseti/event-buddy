package com.semblanceoffunctionality.eventbuddy.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semblanceoffunctionality.eventbuddy.data.Event
import com.semblanceoffunctionality.eventbuddy.data.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventListViewModel @Inject internal constructor(
    private val eventRepository: EventRepository,
) : ViewModel() {
    fun addItem(eventName: String, eventStart: String, eventEnd: String) {
        CoroutineScope(Dispatchers.IO).launch {
            eventRepository.addEvent(Event(eventName, eventStart, eventEnd))
        }
    }

    val items: LiveData<List<Event>> = eventRepository.getEvents()
}