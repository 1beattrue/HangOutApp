package edu.mirea.onebeattrue.hangoutapp.domain.usecases

import edu.mirea.onebeattrue.hangoutapp.domain.AuthRepository

class LogOutUseCase(private val repository: AuthRepository) {
    operator fun invoke() {
        repository.logOut()
    }
}