package edu.mirea.onebeattrue.hangoutapp.di.eventlist

import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck
import edu.mirea.onebeattrue.hangoutapp.data.eventlist.EventListRepositoryImpl
import edu.mirea.onebeattrue.hangoutapp.domain.eventlist.EventListRepository

@DisableInstallInCheck
@Module
interface EventListModule {

    @Binds
    fun bindEventListRepository(impl: EventListRepositoryImpl): EventListRepository
}