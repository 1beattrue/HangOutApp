package edu.mirea.onebeattrue.hangoutapp.data.eventlist

import edu.mirea.onebeattrue.hangoutapp.domain.eventlist.EventListRepository
import edu.mirea.onebeattrue.hangoutapp.domain.eventlist.entities.EventItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EventListRepositoryImpl(
    private val eventListDao: EventListDao,
    private val mapper: EventListMapper
) : EventListRepository {
    override suspend fun addEventItem(eventItem: EventItem) {
        eventListDao.addEventItem(mapper.mapEntityToDbModel(eventItem))
    }

    override suspend fun deleteEventItem(eventItem: EventItem) {
        eventListDao.deleteEventItem(eventItem.id)
    }

    override suspend fun editEventItem(eventItem: EventItem) {
        eventListDao.addEventItem(mapper.mapEntityToDbModel(eventItem))
    }

    override suspend fun getEventItem(eventItemId: Int): EventItem {
        val dbModel = eventListDao.getEventItem(eventItemId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun getEventList(): Flow<List<EventItem>> = eventListDao.getEventList().map {
        mapper.mapListDbModelToListEntity(it)
    }
}