package edu.mirea.onebeattrue.hangoutapp.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.mirea.onebeattrue.hangoutapp.domain.Resource
import edu.mirea.onebeattrue.hangoutapp.domain.AuthRepository
import edu.mirea.onebeattrue.hangoutapp.domain.usecases.LogInUseCase
import edu.mirea.onebeattrue.hangoutapp.domain.usecases.LogOutUseCase
import edu.mirea.onebeattrue.hangoutapp.domain.usecases.SignUpUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    private val _loginFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Resource<FirebaseUser>?>
        get() = _loginFlow

    private val _signupFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val signupFlow: StateFlow<Resource<FirebaseUser>?>
        get() = _signupFlow

    val currentUser: FirebaseUser?
        get() = repository.currentUser
    private val logInUseCase = LogInUseCase(repository)
    private val signUpUseCase = SignUpUseCase(repository)
    private val logOutUseCase = LogOutUseCase(repository)

    init {
        if (repository.currentUser != null) {
            _loginFlow.value = Resource.Success(repository.currentUser!!)
        }
    }

    fun logIn(email: String, password: String) {
        viewModelScope.launch {
            _loginFlow.value = Resource.Loading
            val result = logInUseCase(email, password)
            _loginFlow.value = result
        }
    }

    fun signUp(username: String, email: String, password: String) {
        viewModelScope.launch {
            _signupFlow.value = Resource.Loading
            val result = signUpUseCase(username, email, password)
            _signupFlow.value = result
        }
    }

    fun logOut(email: String, password: String) {
        logOutUseCase
        _loginFlow.value = null
        _signupFlow.value = null
    }
}