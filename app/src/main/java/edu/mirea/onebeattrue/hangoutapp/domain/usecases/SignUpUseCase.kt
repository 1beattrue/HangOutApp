package edu.mirea.onebeattrue.hangoutapp.domain.usecases

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import edu.mirea.onebeattrue.hangoutapp.domain.AuthRepository

class SignUpUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(username: String, email: String, password: String): Task<AuthResult> {
        return repository.signUp(username, email, password)
    }
}