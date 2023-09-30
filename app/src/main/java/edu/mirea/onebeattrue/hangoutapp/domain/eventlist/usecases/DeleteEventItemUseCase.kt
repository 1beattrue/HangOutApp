package edu.mirea.onebeattrue.hangoutapp.domain.eventlist.usecases

import edu.mirea.onebeattrue.hangoutapp.domain.eventlist.EventListRepository
import edu.mirea.onebeattrue.hangoutapp.domain.eventlist.entities.EventItem
import javax.inject.Inject

class DeleteEventItemUseCase @Inject constructor(
    private val repository: EventListRepository
) {
    suspend operator fun invoke(eventItem: EventItem) {
        repository.deleteEventItem(eventItem)
    }
}