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
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

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
                coroutineScope.launch {
                    if (task.isSuccessful) {
                        while (true) {
                            if (currentUser != null) {
                                val profileUpdate = UserProfileChangeRequest.Builder().setDisplayName(username).build()
                                currentUser!!.updateProfile(profileUpdate)
                                break
                                // TODO: подумать чо сделать с этим говнокодом (но учесть, что это работает достаточно неплохо)
                            }
                        }
                    }
                }
            }
    }

    override fun logOut() {
        firebaseAuth.signOut()
    }

}