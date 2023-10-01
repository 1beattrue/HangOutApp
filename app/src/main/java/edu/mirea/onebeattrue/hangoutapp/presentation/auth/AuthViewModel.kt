package edu.mirea.onebeattrue.hangoutapp.presentation.auth

import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import edu.mirea.onebeattrue.hangoutapp.data.Resource
import edu.mirea.onebeattrue.hangoutapp.domain.auth.AuthRepository
import edu.mirea.onebeattrue.hangoutapp.domain.auth.usecases.LogInUseCase
import edu.mirea.onebeattrue.hangoutapp.domain.auth.usecases.LogOutUseCase
import edu.mirea.onebeattrue.hangoutapp.domain.auth.usecases.SignUpUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private val _isUsernameError = MutableStateFlow<Boolean>(false)
    val isUsernameError = _isUsernameError.asStateFlow()

    private val _isEmailError = MutableStateFlow<Boolean>(false)
    val isEmailError = _isEmailError.asStateFlow()

    private val _isPasswordError = MutableStateFlow<Boolean>(false)
    val isPasswordError = _isPasswordError.asStateFlow()

    private val _loginFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val loginFlow = _loginFlow.asStateFlow()

    private val _signupFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val signupFlow = _signupFlow.asStateFlow()

    init {
        if (repository.currentUser != null) {
            _loginFlow.value = Resource.Success(repository.currentUser!!)
        }
    }

    fun login(email: String, password: String) {
        if (!isFieldsValid(username = DEFAULT_USERNAME, email = email, password = password)) return

        viewModelScope.launch {
            _loginFlow.value = Resource.Loading
            val result = logInUseCase(email, password)
            _loginFlow.value = result
        }
    }

    fun signup(username: String, email: String, password: String) {
        if (!isFieldsValid(username = username, email = email, password = password)) return

        viewModelScope.launch {
            _signupFlow.value = Resource.Loading
            val result = signUpUseCase(username, email, password)
            _signupFlow.value = result
        }
    }

    fun logOut() {
        logOutUseCase()
        _loginFlow.value = null
        _signupFlow.value = null
    }

    fun resetErrorInputUsername() {
        _isUsernameError.value = false
    }

    fun resetErrorInputEmail() {
        _isEmailError.value = false
    }

    fun resetErrorInputPassword() {
        _isPasswordError.value = false
    }

    private fun isFieldsValid(
        username: String = DEFAULT_USERNAME,
        email: String,
        password: String
    ): Boolean {
        var result = true
        if (!isValidUsername(username)) {
            _isUsernameError.value = true
            result = false
        }
        if (!isValidEmail(email)) {
            _isEmailError.value = true
            result = false
        }
        if (!isValidPassword(password)) {
            _isPasswordError.value = true
            result = false
        }
        return result
    }

    private fun isValidUsername(username: String): Boolean {
        return username.trim().isNotEmpty() && username.trim().length <= 30
    }

    private fun isValidEmail(email: String): Boolean {
        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        return password.trim().length >= 8
    }

    companion object {
        private const val DEFAULT_USERNAME = "XXX"
    }
}