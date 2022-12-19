package com.semblanceoffunctionality.eventbuddy.ui.data

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.semblanceoffunctionality.eventbuddy.data.EventRepository
import com.semblanceoffunctionality.eventbuddy.data.PersonalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(
    private val eventRepository: EventRepository,
    private val personalRepository: PersonalRepository,
)  : ViewModel() {
    private var gson: Gson? = null

    fun exportData() = runBlocking {
        gson = GsonBuilder().serializeNulls().setPrettyPrinting().create()

        val events = eventRepository.getEvents().first()
        val info = personalRepository.getInfo().first()

        val allData = BackupData(events, info)
        return@runBlocking gson?.toJson(allData)
    }

    fun importData(content: String) {
        val data = Gson().fromJson(content, BackupData::class.java)

        runBlocking {
            eventRepository.createEvents(data.events)
            personalRepository.importInfo(data.info)
        }
    }
}