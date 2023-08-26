package edu.mirea.onebeattrue.hangoutapp.presentation.auth

import android.util.Log
import android.view.View
import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import edu.mirea.onebeattrue.hangoutapp.data.AuthRepositoryImpl
import edu.mirea.onebeattrue.hangoutapp.domain.usecases.LogInUseCase
import edu.mirea.onebeattrue.hangoutapp.domain.usecases.LogOutUseCase
import edu.mirea.onebeattrue.hangoutapp.domain.usecases.SignUpUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//@HiltViewModel
//class AuthViewModel @Inject constructor(
//    private val repository: AuthRepository
//) : ViewModel() {

class AuthViewModel : ViewModel() {
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

    private val repository = AuthRepositoryImpl(FirebaseAuth.getInstance()) // TODO: разобраться с dagger и убрать это убожество

    val currentUser: FirebaseUser?
        get() = repository.currentUser
    private val logInUseCase = LogInUseCase(repository)
    private val signUpUseCase = SignUpUseCase(repository)
    private val logOutUseCase = LogOutUseCase(repository)

    fun logIn(email: String, password: String, onErrorCallback: (String) -> Unit) {
        val fieldsValid = isValidEmail(email) && isValidPassword(password)
        if (fieldsValid) {
            viewModelScope.launch {
                _progressBarVisibility.postValue(true)
                logInUseCase(email, password) {
                    if (it.isSuccessful) {
                        Log.d("AuthViewModel", "signInWithEmail:success")
                        finishAuthorization()
                    } else {
                        Log.d("AuthViewModel", it.exception?.message!!)
                        onErrorCallback(it.exception?.message!!)
                    }
                }
                _progressBarVisibility.postValue(false)
            }
        }
    }

    fun signUp(username: String, email: String, password: String, onErrorCallback: (String) -> Unit) {
        val fieldsValid = isValidUsername(username) && isValidEmail(email) && isValidPassword(password)
        if (fieldsValid) {
            viewModelScope.launch {
                _progressBarVisibility.postValue(true)
                signUpUseCase(username, email, password) {
                    if (it.isSuccessful) {
                        Log.d("AuthViewModel", "signInWithEmail:success")
                        finishAuthorization()
                    } else {
                        Log.d("AuthViewModel", it.exception?.message!!)
                        onErrorCallback(it.exception?.message!!)
                    }
                }
                _progressBarVisibility.postValue(false)
            }
        }
    }

    fun logOut() {
        logOutUseCase
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
        val result = username.length <= 30
        if (!result) _errorInputUsername.value = true
        return result
    }

    private fun isValidEmail(email: String): Boolean {
        val result = PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
        if (!result) _errorInputEmail.value = true
        return result
    }

    private fun isValidPassword(password: String): Boolean {
        val result = password.length >= 8
        if (!result) _errorInputPassword.value = true
        return result
    }

    private fun finishAuthorization() {
        _shouldFinishAuthorization.postValue(Unit)
    }
}