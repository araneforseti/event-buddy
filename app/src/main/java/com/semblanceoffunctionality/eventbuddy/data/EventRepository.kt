package com.semblanceoffunctionality.eventbuddy.data

import androidx.lifecycle.LiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventRepository @Inject constructor(private val eventDao: EventDao) {
    fun getEvents(): LiveData<List<Event>> {
        return eventDao.getAll()
    }

    suspend fun addEvent(event: Event) {
        return eventDao.insertAll(listOf(event))
    }
}