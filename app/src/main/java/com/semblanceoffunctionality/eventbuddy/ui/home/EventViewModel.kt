package com.semblanceoffunctionality.eventbuddy.ui.home

import androidx.lifecycle.ViewModel
import com.semblanceoffunctionality.eventbuddy.data.Event

class EventViewModel(val event: Event) : ViewModel() {
    val name
        get() = event.name
    val start
        get() = event.dateStart
    val end
        get() = event.dateEnd
}