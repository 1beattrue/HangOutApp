package edu.mirea.onebeattrue.hangoutapp.domain.usecases

import com.google.firebase.auth.FirebaseUser
import edu.mirea.onebeattrue.hangoutapp.domain.Resource
import edu.mirea.onebeattrue.hangoutapp.domain.AuthRepository

class SignUpUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(username: String, email: String, password: String): Resource<FirebaseUser> {
        return repository.signUp(username, email, password)
    }
}