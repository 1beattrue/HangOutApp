package edu.mirea.onebeattrue.hangoutapp.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey
import edu.mirea.onebeattrue.hangoutapp.presentation.auth.AuthViewModel

@DisableInstallInCheck
@Module
interface ViewModelModule {
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    @Binds
    fun bindAuthViewModel(impl: AuthViewModel): ViewModel

    // ... добавлять аналогичные методы для других ViewModel'ей
}