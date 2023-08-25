package edu.mirea.onebeattrue.hangoutapp.domain

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun logIn(email: String, password: String): Resource<FirebaseUser>
    suspend fun signUp(username: String, email: String, password: String): Resource<FirebaseUser>
    fun logOut()
}