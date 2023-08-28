package edu.mirea.onebeattrue.hangoutapp.di

import dagger.Component
import edu.mirea.onebeattrue.hangoutapp.presentation.auth.LoginFragment
import edu.mirea.onebeattrue.hangoutapp.presentation.auth.RegisterFragment

@Component(modules = [AuthModule::class])
interface Component {
    fun inject(fragment: LoginFragment)
    fun inject(fragment: RegisterFragment)
}