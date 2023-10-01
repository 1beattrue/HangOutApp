package edu.mirea.onebeattrue.hangoutapp.domain.eventlist.entities

data class EventItem(
    val name: String,
    val date: String,
    val time: String,
    val location: String,
    var id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}