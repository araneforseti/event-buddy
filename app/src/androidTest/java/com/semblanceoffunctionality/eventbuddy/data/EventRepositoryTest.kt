package com.semblanceoffunctionality.eventbuddy.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EventRepositoryTest {
    private lateinit var database: AppDatabase
    private lateinit var eventDao: EventDao
    private lateinit var eventRepository: EventRepository
    private val eventA = Event("eventA", "Event A")

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        eventDao = database.eventDao()
        eventRepository = EventRepository(eventDao)
        eventDao.insertAll(listOf(eventA))
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testGetEvent() = runBlocking {
        val eventResult = eventRepository.getEvent(eventA.name).first()

        assertThat(eventResult.name, equalTo(eventA.name))
    }

    @Test
    fun testGetEvent_WhenNotExists() = runBlocking {
        val eventResult = eventRepository.getEvent("fadweavwa").first()

        assertThat(eventResult, equalTo(null))
    }
}