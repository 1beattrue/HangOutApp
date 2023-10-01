package edu.mirea.onebeattrue.hangoutapp.data.eventlist.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event_items")
data class EventItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val date: String,
    val time: String,
    val location: String
)