package edu.mirea.onebeattrue.hangoutapp.data.eventlist

import edu.mirea.onebeattrue.hangoutapp.domain.eventlist.EventListRepository
import edu.mirea.onebeattrue.hangoutapp.domain.eventlist.entities.EventItem
import kotlinx.coroutines.flow.Flow

class EventListRepositoryImpl : EventListRepository {
    override suspend fun addEventItem(eventItem: EventItem) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteEventItem(eventItem: EventItem) {
        TODO("Not yet implemented")
    }

    override suspend fun editEventItem(eventItem: EventItem) {
        TODO("Not yet implemented")
    }

    override suspend fun getEventItem(eventItemId: Int): EventItem {
        TODO("Not yet implemented")
    }

    override fun getEventList(): Flow<List<EventItem>> {
        TODO("Not yet implemented")
    }
}