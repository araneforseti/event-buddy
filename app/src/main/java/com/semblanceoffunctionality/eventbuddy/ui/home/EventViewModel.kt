package com.semblanceoffunctionality.eventbuddy.ui.home

import com.semblanceoffunctionality.eventbuddy.data.Event

class EventViewModel(val event: Event) {
    val name
        get() = event.name
    val start
        get() = event.dateStart
    val end
        get() = event.dateEnd
}