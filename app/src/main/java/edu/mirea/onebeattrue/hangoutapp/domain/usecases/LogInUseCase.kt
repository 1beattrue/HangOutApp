package edu.mirea.onebeattrue.hangoutapp.domain.usecases

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import edu.mirea.onebeattrue.hangoutapp.domain.AuthRepository
import javax.inject.Inject

class LogInUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Task<AuthResult> {
        return repository.logIn(email, password)
    }
}