package edu.mirea.onebeattrue.hangoutapp.presentation.auth

sealed class AuthState

data class ErrorMessage(val message: String?) : AuthState()
data object Progress : AuthState()
data object Finish : AuthState()
