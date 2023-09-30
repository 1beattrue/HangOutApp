package edu.mirea.onebeattrue.hangoutapp.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import edu.mirea.onebeattrue.hangoutapp.data.eventlist.entities.EventItemDbModel
import edu.mirea.onebeattrue.hangoutapp.data.eventlist.EventListDao

@Database(entities = [EventItemDbModel::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun eventListDao(): EventListDao

    companion object {
        private var INSTANCE: LocalDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "event_item.db"

        fun getInstance(application: Application): LocalDatabase {
            INSTANCE?.let {
                return it
            }

            // double check
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }

                val database = Room.databaseBuilder(
                    application,
                    LocalDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = database
                return database
            }
        }
    }
}