package edu.mirea.onebeattrue.hangoutapp.domain.eventlist

import edu.mirea.onebeattrue.hangoutapp.domain.eventlist.entities.EventItem
import kotlinx.coroutines.flow.Flow

interface EventListRepository {
    suspend fun addEventItem(eventItem: EventItem)
    suspend fun deleteEventItem(eventItem: EventItem)
    suspend fun editEventItem(eventItem: EventItem)
    suspend fun getEventItem(eventItemId: Int): EventItem
    fun getEventList(): Flow<List<EventItem>>
}