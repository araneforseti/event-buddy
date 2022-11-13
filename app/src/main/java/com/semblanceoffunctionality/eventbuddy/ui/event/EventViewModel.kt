package com.semblanceoffunctionality.eventbuddy.ui.event

import androidx.lifecycle.ViewModel
import com.semblanceoffunctionality.eventbuddy.data.Event

class EventViewModel(eventName: String) : ViewModel() {
    val event = Event(eventName, "01/01/2023", "01/12/2023")

    val name
        get() = event.name
    val start
        //get() = event.dateStart
        get() = "01/01/2023"
    val end
        //get() = event.dateEnd
        get() = "30/12/2023"
}