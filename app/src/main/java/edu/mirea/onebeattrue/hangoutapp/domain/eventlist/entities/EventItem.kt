package edu.mirea.onebeattrue.hangoutapp.domain.eventlist.entities

import java.sql.Time
import java.sql.Date

data class EventItem(
    val name: String,
    val date: Date,
    val time: Time,
    val location: String,
    var id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}