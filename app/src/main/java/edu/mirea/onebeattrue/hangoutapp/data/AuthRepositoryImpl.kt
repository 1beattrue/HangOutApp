package edu.mirea.onebeattrue.hangoutapp.data

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import edu.mirea.onebeattrue.hangoutapp.domain.AuthRepository

class AuthRepositoryImpl(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun logIn(
        email: String,
        password: String,
        callback: (Task<AuthResult>) -> Unit
    ) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                callback(task)
            }
    }

    override suspend fun signUp(
        username: String,
        email: String,
        password: String,
        callback: (Task<AuthResult>) -> Unit
    ) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                callback(task)
            }
    }

    override fun logOut() {
        firebaseAuth.signOut()
    }

}