package com.semblanceoffunctionality.eventbuddy.data

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Query("SELECT * FROM events ORDER BY dateStart")
    fun getAll(): Flow<List<Event>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(events: List<Event>)

    @Query("SELECT * FROM events WHERE name = :eventName")
    fun getEventByName(eventName: String): Flow<Event>

    @Update
    fun updateEvent(event: Event)

    @Delete
    fun deleteEvent(event: Event)
}