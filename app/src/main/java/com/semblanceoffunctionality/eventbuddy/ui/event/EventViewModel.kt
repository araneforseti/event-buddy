package com.semblanceoffunctionality.eventbuddy.ui.event

import androidx.lifecycle.ViewModel
import com.semblanceoffunctionality.eventbuddy.data.Event

class EventViewModel(eventName: String) : ViewModel() {
    val event = Event(eventName, "01/01/2023", "01/12/2023")

    val name
        get() = event.name
    val start
        get() = event.dateStart
    val end
        get() = event.dateEnd
    val website
        get() = event.website
    val facebook
        get() = event.facebook
    val scoring
        get() = event.scoring
    val venue
        get() = event.venue
    val hotelInfo
        get() = event.hotelInfo
    val hotelBooked
        get() = event.hotelBooked
    val roomies
        get() = event.roomies
    val price
        get() = event.price
    val travelBooked
        get() = event.travelBooked
    val travelInfo
        get() = event.travelInfo

}