package edu.mirea.onebeattrue.hangoutapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.mirea.onebeattrue.hangoutapp.domain.AuthRepository
import edu.mirea.onebeattrue.hangoutapp.presentation.auth.AuthViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val repository: AuthRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass == AuthViewModel::class.java) {
            return AuthViewModel(repository) as T
        }
        throw RuntimeException("Unknown view model class $modelClass")
    }
}