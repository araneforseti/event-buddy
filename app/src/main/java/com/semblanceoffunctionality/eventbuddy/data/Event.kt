package com.semblanceoffunctionality.eventbuddy.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "events")
data class Event(
    @PrimaryKey @ColumnInfo(name = "name") var name: String,
    var dateStart: String="Now",
    var dateEnd: String="Later",
    var state: String="No"
)