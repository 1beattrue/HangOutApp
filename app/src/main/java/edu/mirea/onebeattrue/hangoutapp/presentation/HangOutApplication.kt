package edu.mirea.onebeattrue.hangoutapp.presentation

import android.app.Application
import edu.mirea.onebeattrue.hangoutapp.di.DaggerApplicationComponent

class HangOutApplication : Application() {
    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}