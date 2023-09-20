package edu.mirea.onebeattrue.hangoutapp.presentation.auth

import android.util.Log
import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import edu.mirea.onebeattrue.hangoutapp.domain.auth.AuthRepository
import edu.mirea.onebeattrue.hangoutapp.domain.auth.usecases.LogInUseCase
import edu.mirea.onebeattrue.hangoutapp.domain.auth.usecases.LogOutUseCase
import edu.mirea.onebeattrue.hangoutapp.domain.auth.usecases.SignUpUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val logInUseCase: LogInUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val logOutUseCase: LogOutUseCase
) : ViewModel() {
    val currentUser: FirebaseUser?
        get() = repository.currentUser

    private var _authError = MutableLiveData<String>()
    val authError: LiveData<String>
        get() = _authError

    private var _shouldFinishAuthorization = MutableLiveData<Unit>()
    val shouldFinishAuthorization: LiveData<Unit>
        get() = _shouldFinishAuthorization

    private var _errorInputUsername = MutableLiveData<Boolean>()
    val errorInputUsername: LiveData<Boolean>
        get() = _errorInputUsername

    private var _errorInputEmail = MutableLiveData<Boolean>()
    val errorInputEmail: LiveData<Boolean>
        get() = _errorInputEmail

    private var _errorInputPassword = MutableLiveData<Boolean>()
    val errorInputPassword: LiveData<Boolean>
        get() = _errorInputPassword

    private val _progressBarVisibility = MutableLiveData<Boolean>()
    val progressBarVisibility: LiveData<Boolean>
        get() = _progressBarVisibility

    fun logIn(email: String, password: String) {
        val fieldsValid = isValidEmail(email) && isValidPassword(password)
        if (fieldsValid) {
            _progressBarVisibility.value = true
            viewModelScope.launch {
                val result = logInUseCase(email, password)
                if (result.isSuccessful) {
                    Log.d("AuthViewModel", "logIn:success")
                    finishAuthorization()
                } else {
                    Log.d("AuthViewModel", result.exception?.message!!)
                    showAuthorizationError(result.exception?.message!!)
                }
                _progressBarVisibility.value = false
            }
        }
    }

    fun signUp(username: String, email: String, password: String) {
        val fieldsValid = isValidUsername(username)
                && isValidEmail(email)
                && isValidPassword(password)
        if (fieldsValid) {
            _progressBarVisibility.value = true
            viewModelScope.launch {
                val result = signUpUseCase(username, email, password)
                if (result.isSuccessful) {
                    Log.d("AuthViewModel", "signUp:success")
                    finishAuthorization()
                } else {
                    Log.d("AuthViewModel", result.exception?.message!!)
                    showAuthorizationError(result.exception?.message!!)
                }
                _progressBarVisibility.value = false
            }
        }
    }

    fun logOut() {
        logOutUseCase()
    }

    fun resetErrorInputUsername() {
        _errorInputUsername.value = false
    }

    fun resetErrorInputEmail() {
        _errorInputEmail.value = false
    }

    fun resetErrorInputPassword() {
        _errorInputPassword.value = false
    }

    private fun isValidUsername(username: String): Boolean {
        val result = username.trim().isNotEmpty() && username.trim().length <= 30
        if (!result) _errorInputUsername.value = true
        return result
    }

    private fun isValidEmail(email: String): Boolean {
        val result = PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
        if (!result) _errorInputEmail.value = true
        return result
    }

    private fun isValidPassword(password: String): Boolean {
        val result = password.trim().length >= 8
        if (!result) _errorInputPassword.value = true
        return result
    }

    private fun finishAuthorization() {
        _shouldFinishAuthorization.value = Unit
    }

    private fun showAuthorizationError(message: String) {
        _authError.value = message
    }
}