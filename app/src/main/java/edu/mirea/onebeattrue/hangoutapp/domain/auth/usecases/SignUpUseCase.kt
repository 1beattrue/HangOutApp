package edu.mirea.onebeattrue.hangoutapp.domain.auth.usecases

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import edu.mirea.onebeattrue.hangoutapp.domain.auth.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(username: String, email: String, password: String): Task<AuthResult> {
        return repository.signUp(username, email, password)
    }
}