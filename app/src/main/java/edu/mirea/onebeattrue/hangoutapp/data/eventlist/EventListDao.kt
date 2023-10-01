package edu.mirea.onebeattrue.hangoutapp.data.eventlist

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import edu.mirea.onebeattrue.hangoutapp.data.eventlist.entities.EventItemDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface EventListDao {
    @Query("SELECT * FROM event_items")
    fun getEventList(): Flow<List<EventItemDbModel>> // TODO: разобраться почему не работает suspend

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addEventItem(eventListDbModel: EventItemDbModel)

    @Query("DELETE FROM event_items WHERE id =:eventItemId")
    fun deleteEventItem(eventItemId: Int)

    @Query("SELECT * FROM event_items WHERE id=:eventItemId LIMIT 1")
    fun getEventItem(eventItemId: Int): EventItemDbModel
}