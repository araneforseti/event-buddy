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
class EventDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var eventDao: EventDao
    private val eventA = Event("eventA", "Event A")
    private val eventB = Event("eventB", "Event B")
    private val eventC = Event("eventC", "Event C")

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        eventDao = database.eventDao()
        eventDao.insertAll(listOf(eventA, eventB, eventC))
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testGetEvents() = runBlocking {
        val eventList = eventDao.getAll().first()
        assertThat(eventList.size, equalTo(3))
        assertThat(eventList[0].name, equalTo(eventA.name))
        assertThat(eventList[1].name, equalTo(eventB.name))
        assertThat(eventList[2].name, equalTo(eventC.name))
    }

    @Test
    fun testGetEventByName() = runBlocking {
        val result = eventDao.getEventByName(eventA.name).first()

        assertThat(result.name, equalTo(eventA.name))
    }

    @Test
    fun testGetEventByName_WhenNotExist() = runBlocking {
        val result = eventDao.getEventByName("gsfawkeafhweu").first()

        assertThat(result, equalTo(null))
    }
}