package edu.mirea.onebeattrue.hangoutapp.data.eventlist.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.sql.Time

@Entity(tableName = "event_items")
data class EventItemDbModel(
    val name: String,
    val date: Date,
    val time: Time,
    val location: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int
)