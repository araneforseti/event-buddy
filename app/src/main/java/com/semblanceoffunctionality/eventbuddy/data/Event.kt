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
    var website: String = "",
    var facebook: String = "",
    var scoring: String = "",
    var venue: String = "",
    var travelBooked: Boolean = false,
    var travelInfo: String = "",
    var hotelBooked: Boolean = false,
    var hotelInfo: String = "",
    var roomes: String = "",
    var price: String = ""
)