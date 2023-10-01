package edu.mirea.onebeattrue.hangoutapp.domain.eventlist.usecases

import edu.mirea.onebeattrue.hangoutapp.domain.eventlist.EventListRepository
import edu.mirea.onebeattrue.hangoutapp.domain.eventlist.entities.EventItem
import javax.inject.Inject

class GetEventList @Inject constructor(
    private val repository: EventListRepository
) {
    operator fun invoke() = repository.getEventList()
}