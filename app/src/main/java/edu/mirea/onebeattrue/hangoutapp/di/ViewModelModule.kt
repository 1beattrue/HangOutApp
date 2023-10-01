package edu.mirea.onebeattrue.hangoutapp.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck
import dagger.multibindings.IntoMap
import edu.mirea.onebeattrue.hangoutapp.presentation.auth.AuthViewModel
import edu.mirea.onebeattrue.hangoutapp.presentation.eventlist.EventListViewModel

@DisableInstallInCheck
@Module
interface ViewModelModule {
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    @Binds
    fun bindAuthViewModel(impl: AuthViewModel): ViewModel

    @IntoMap
    @ViewModelKey(EventListViewModel::class)
    @Binds
    fun bindEventListViewModel(impl: EventListViewModel): ViewModel

    // ... добавлять аналогичные методы для других ViewModel'ей
}