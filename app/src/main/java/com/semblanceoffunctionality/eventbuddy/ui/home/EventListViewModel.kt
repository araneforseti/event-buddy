package com.semblanceoffunctionality.eventbuddy.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semblanceoffunctionality.eventbuddy.data.Event
import dagger.hilt.android.lifecycle.HiltViewModel

class EventListViewModel : ViewModel() {
    private val buda : Event = Event("Budafest")
    private val king : Event = Event("King Swing")
    private val tlv : Event = Event("TLV")
    private val list = arrayListOf(buda, king, tlv)
    val items = MutableLiveData<List<Event>>().apply {
        value = list
    }
}