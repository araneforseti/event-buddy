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

    fun getEvent(eventName: String): Flow<Event> {
        println("----")
        println("-- name passed --")
        println(eventName)
        println("----")
        var result = eventDao.getEventByName(eventName)
        println("-- result --")
        println(result.asLiveData().value)
        println("----")
        return result
    }

    fun updateEvent(event: Event) = eventDao.updateEvent(event)
}