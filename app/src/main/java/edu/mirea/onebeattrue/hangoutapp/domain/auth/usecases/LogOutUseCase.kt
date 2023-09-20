package edu.mirea.onebeattrue.hangoutapp.domain.auth.usecases

import edu.mirea.onebeattrue.hangoutapp.domain.auth.AuthRepository
import javax.inject.Inject

class LogOutUseCase @Inject constructor(private val repository: AuthRepository) {
    operator fun invoke() {
        repository.logOut()
    }
}