package edu.mirea.onebeattrue.hangoutapp.domain.usecases

import com.google.firebase.auth.FirebaseUser
import edu.mirea.onebeattrue.hangoutapp.domain.Resource
import edu.mirea.onebeattrue.hangoutapp.domain.AuthRepository

class LogInUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Resource<FirebaseUser> {
        return repository.logIn(email, password)
    }
}