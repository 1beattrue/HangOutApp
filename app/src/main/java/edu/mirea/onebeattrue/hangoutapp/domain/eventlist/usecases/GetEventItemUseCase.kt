package edu.mirea.onebeattrue.hangoutapp.domain.eventlist.usecases

import edu.mirea.onebeattrue.hangoutapp.domain.eventlist.EventListRepository
import javax.inject.Inject

class GetEventItemUseCase @Inject constructor(
    private val repository: EventListRepository
) {
    suspend operator fun invoke(eventItemId: Int) = repository.getEventItem(eventItemId)
}