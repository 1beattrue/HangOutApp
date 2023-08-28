package edu.mirea.onebeattrue.hangoutapp.domain.usecases

import edu.mirea.onebeattrue.hangoutapp.domain.AuthRepository
import javax.inject.Inject

class LogOutUseCase @Inject constructor(private val repository: AuthRepository) {
    operator fun invoke() {
        repository.logOut()
    }
}