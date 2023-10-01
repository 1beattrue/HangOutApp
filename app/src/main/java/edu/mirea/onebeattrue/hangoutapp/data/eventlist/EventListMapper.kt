package edu.mirea.onebeattrue.hangoutapp.data.eventlist

import edu.mirea.onebeattrue.hangoutapp.data.eventlist.entities.EventItemDbModel
import edu.mirea.onebeattrue.hangoutapp.domain.eventlist.entities.EventItem
import javax.inject.Inject

class EventListMapper @Inject constructor() {
    fun mapEntityToDbModel(eventItem: EventItem) = EventItemDbModel(
        id = eventItem.id,
        name = eventItem.name,
        date = eventItem.date,
        time = eventItem.time,
        location = eventItem.location
    )

    fun mapDbModelToEntity(eventItemDbModel: EventItemDbModel) = EventItem(
        id = eventItemDbModel.id,
        name = eventItemDbModel.name,
        date = eventItemDbModel.date,
        time = eventItemDbModel.time,
        location = eventItemDbModel.location
    )

    fun mapListDbModelToListEntity(list: List<EventItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}