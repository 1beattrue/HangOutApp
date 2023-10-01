package edu.mirea.onebeattrue.hangoutapp.presentation.eventlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.mirea.onebeattrue.hangoutapp.domain.eventlist.entities.EventItem
import edu.mirea.onebeattrue.hangoutapp.domain.eventlist.usecases.AddEventItemUseCase
import edu.mirea.onebeattrue.hangoutapp.domain.eventlist.usecases.DeleteEventItemUseCase
import edu.mirea.onebeattrue.hangoutapp.domain.eventlist.usecases.EditEventItemUseCase
import edu.mirea.onebeattrue.hangoutapp.domain.eventlist.usecases.GetEventList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class EventListViewModel @Inject constructor(
    private val addEventItemUseCase: AddEventItemUseCase,
    private val deleteEventItemUseCase: DeleteEventItemUseCase,
    private val editEventItemUseCase: EditEventItemUseCase,
    private val getEventItemUseCase: AddEventItemUseCase,
    private val getEventList: GetEventList
) : ViewModel() {

    val list: Flow<List<EventItem>> = getEventList()

    fun addEvent(eventItem: EventItem) {
        viewModelScope.launch(Dispatchers.IO) {
            addEventItemUseCase(eventItem)
        }
    }

    fun deleteEvent(eventItem: EventItem) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteEventItemUseCase(eventItem)
        }
    }
}