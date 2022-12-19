package com.semblanceoffunctionality.eventbuddy.data

import androidx.room.Entity
import androidx.room.PrimaryKey

// TODO: Do this better, cause this is the worst way to do this
@Entity(tableName = "personal")
data class Personal(
    var name: String = "",
    var number: String = "",
    var divisionLeader: String = "Newcomer",
    var pointsLeader: String = "0",
    var neededPointsLeader: String = "",
    var divisionFollower: String = "Newcomer",
    var pointsFollower: String = "0",
    var neededPointsFollower: String = "",
    @PrimaryKey(autoGenerate = true) var id: Int = 0
)