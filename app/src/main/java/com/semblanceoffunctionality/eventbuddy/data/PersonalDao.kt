package com.semblanceoffunctionality.eventbuddy.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonalDao {
    @Query("SELECT * FROM personal")
    fun getAll(): Flow<List<Personal>>

    @Query("SELECT * FROM personal LIMIT 1")
    fun getInfo(): Flow<Personal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(events: List<Personal>)

    @Update
    fun update(personal: Personal)
}