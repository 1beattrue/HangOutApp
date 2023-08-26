package edu.mirea.onebeattrue.hangoutapp.domain

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun logIn(email: String, password: String, callback: (Task<AuthResult>) -> Unit)
    suspend fun signUp(username: String, email: String, password: String, callback: (Task<AuthResult>) -> Unit)
    fun logOut()
}