package edu.mirea.onebeattrue.hangoutapp.di

import dagger.Component
import edu.mirea.onebeattrue.hangoutapp.di.auth.AuthModule
import edu.mirea.onebeattrue.hangoutapp.di.eventlist.EventListModule
import edu.mirea.onebeattrue.hangoutapp.presentation.auth.LoginFragment
import edu.mirea.onebeattrue.hangoutapp.presentation.auth.RegisterFragment
import edu.mirea.onebeattrue.hangoutapp.presentation.profile.ProfileFragment

@Component(modules = [EventListModule::class, AuthModule::class, ViewModelModule::class])
interface Component {
    fun inject(fragment: LoginFragment)
    fun inject(fragment: RegisterFragment)
    fun inject(fragment: ProfileFragment)
}