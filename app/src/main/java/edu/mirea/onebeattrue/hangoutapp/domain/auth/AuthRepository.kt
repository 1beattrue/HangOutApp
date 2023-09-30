package edu.mirea.onebeattrue.hangoutapp.domain.auth

import com.google.firebase.auth.FirebaseUser
import edu.mirea.onebeattrue.hangoutapp.data.Resource

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun signup(username: String, email: String, password: String): Resource<FirebaseUser>
    fun logOut()
}