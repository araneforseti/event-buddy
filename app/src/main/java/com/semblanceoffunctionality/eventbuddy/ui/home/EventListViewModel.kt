package com.semblanceoffunctionality.eventbuddy.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semblanceoffunctionality.eventbuddy.data.Event
import dagger.hilt.android.lifecycle.HiltViewModel

class EventListViewModel : ViewModel() {
    private val buda : Event = Event("Budafest", "11/01/23", "13/01/23")
    private val king : Event = Event("King Swing", "17/03/23", "21/03/23")
    private val tlv : Event = Event("TLV", "11/05/23", "14/05/23")
    private val list = arrayListOf(buda, king, tlv)
    val items = MutableLiveData<List<Event>>().apply {
        value = list
    }
}