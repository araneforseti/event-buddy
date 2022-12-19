package com.semblanceoffunctionality.eventbuddy.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonalRepository @Inject constructor(private val personalDao: PersonalDao) {
    fun getInfo() = runBlocking {
        val info = personalDao.getInfo().asLiveData().value
        if (info == null) {
            personalDao.insertAll(listOf(Personal()))
        }
        personalDao.getInfo()
    }

    fun updateInfo(info: Personal) = personalDao.update(info)

    suspend fun importInfo(info: Personal) = personalDao.insertAll(listOf(info))
}