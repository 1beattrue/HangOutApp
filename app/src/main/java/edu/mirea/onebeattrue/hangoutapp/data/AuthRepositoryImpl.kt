package edu.mirea.onebeattrue.hangoutapp.data

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import edu.mirea.onebeattrue.hangoutapp.domain.AuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AuthRepositoryImpl(
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
            delay(1000)
            continue
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
            delay(1000)
            continue
        }
        if (authResult!!.isSuccessful) {
            while (currentUser == null) { // TODO: подумать чо сделать с этим говнокодом (но учесть, что это работает достаточно неплохо)
                delay(1000)
                continue
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