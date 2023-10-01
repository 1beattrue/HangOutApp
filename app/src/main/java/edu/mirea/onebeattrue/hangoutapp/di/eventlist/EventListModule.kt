package edu.mirea.onebeattrue.hangoutapp.di.eventlist

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import edu.mirea.onebeattrue.hangoutapp.data.LocalDatabase
import edu.mirea.onebeattrue.hangoutapp.data.eventlist.EventListRepositoryImpl
import edu.mirea.onebeattrue.hangoutapp.domain.eventlist.EventListRepository

@DisableInstallInCheck
@Module
interface EventListModule {

    @Binds
    fun bindEventListRepository(impl: EventListRepositoryImpl): EventListRepository

    companion object {
        @Provides
        fun provideEventListDao(
            application: Application
        ) = LocalDatabase.getInstance(application).eventListDao()
    }
}