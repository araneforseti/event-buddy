package com.semblanceoffunctionality.eventbuddy.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventRepository @Inject constructor(private val eventDao: EventDao) {
    fun getEvents() = eventDao.getAll()

    suspend fun addEvent(event: Event) = eventDao.insertAll(listOf(event))

    fun getEvent(eventName: String) = eventDao.getEventByName(eventName)

    fun updateEvent(event: Event) = eventDao.updateEvent(event)

    suspend fun createEvents(events: List<Event>) = eventDao.insertAll(events)
    fun deleteEvent(event: Event) {
        eventDao.deleteEvent(event)
    }
}