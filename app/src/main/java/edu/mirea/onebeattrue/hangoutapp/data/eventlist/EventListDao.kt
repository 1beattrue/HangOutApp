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
    fun getEventList(): Flow<List<EventItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEventItem(eventListDbModel: EventItemDbModel)

    @Query("DELETE FROM event_items WHERE id =:eventItemId")
    suspend fun deleteEventItem(eventItemId: Int)

    @Query("SELECT * FROM event_items WHERE id=:eventItemId LIMIT 1")
    suspend fun getEventItem(eventItemId: Int): EventItemDbModel
}