package com.semblanceoffunctionality.eventbuddy.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "events")
data class Event(
    @PrimaryKey @ColumnInfo(name = "name") var name: String,
    var dateStart: String="Start TBD",
    var dateEnd: String="End TBD",
    var registered: Boolean = false,
    var website: String = "Website TBD",
    var facebook: String = "Facebook TBD",
    var scoring: String = "Scoring TBD",
    var venue: String = "Venue TBD",
    var travelBooked: Boolean = false,
    var travelInfo: String = "Travel Info tbd",
    var hotelBooked: Boolean = false,
    var hotelInfo: String = "Hotel Info tbd",
    var roomies: String = "Roomies: TBD",
    var price: String = "\$TBD"
)