package edu.mirea.onebeattrue.hangoutapp.data.auth

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import edu.mirea.onebeattrue.hangoutapp.domain.AuthRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun logIn(
        email: String,
        password: String
    ): Task<AuthResult> {
        var authResult: Task<AuthResult>? = null
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                authResult = task
            }
        while (authResult == null) {
            delay(500)
        }
        return authResult!!
    }

    override suspend fun signUp(
        username: String,
        email: String,
        password: String
    ): Task<AuthResult> {
        var authResult: Task<AuthResult>? = null
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                authResult = task
            }
        while (authResult == null) {
            delay(500)
        }
        if (authResult!!.isSuccessful) {
            while (currentUser == null) { // TODO: подумать чо сделать с этим говнокодом (но учесть, что это работает достаточно неплохо)
                delay(500)
            }
            val profileUpdate = UserProfileChangeRequest.Builder().setDisplayName(username).build()
            currentUser!!.updateProfile(profileUpdate)
        }
        return authResult!!
    }

    override fun logOut() {
        firebaseAuth.signOut()
    }
}