package edu.mirea.onebeattrue.hangoutapp.domain.auth.usecases

import com.google.firebase.auth.FirebaseUser
import edu.mirea.onebeattrue.hangoutapp.data.Resource
import edu.mirea.onebeattrue.hangoutapp.domain.auth.AuthRepository
import javax.inject.Inject

class LogInUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Resource<FirebaseUser> {
        return repository.login(email, password)
    }
}