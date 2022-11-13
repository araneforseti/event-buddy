package com.semblanceoffunctionality.eventbuddy.ui.event

import androidx.lifecycle.ViewModel
import com.semblanceoffunctionality.eventbuddy.data.Event

class EventViewModel() : ViewModel() {
    //var name: String = savedStateHandle.get<String>(EVENT_NAME_SAVED_STATE_KEY)!!
    val event = Event("Sample Event", "01/01/2023", "01/12/2023")

    val name
        //get() = event.name
        get() = "Sample Event"
    val start
        //get() = event.dateStart
        get() = "01/01/2023"
    val end
        //get() = event.dateEnd
        get() = "30/12/2023"
}