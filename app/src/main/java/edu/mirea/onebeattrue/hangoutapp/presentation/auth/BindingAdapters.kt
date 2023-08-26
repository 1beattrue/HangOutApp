package edu.mirea.onebeattrue.hangoutapp.presentation.auth

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import edu.mirea.onebeattrue.hangoutapp.R

@BindingAdapter("emailError")
fun bindEmailError(textInputLayout: TextInputLayout, errorInputEmail: Boolean) {
    val message = if (errorInputEmail) {
        textInputLayout.context.getString(R.string.error_input_email)
    } else {
        null
    }
    textInputLayout.error = message
}

@BindingAdapter("passwordError")
fun bindPasswordError(textInputLayout: TextInputLayout, errorInputPassword: Boolean) {
    val message = if (errorInputPassword) {
        textInputLayout.context.getString(R.string.error_input_password)
    } else {
        null
    }
    textInputLayout.error = message
}

@BindingAdapter("usernameError")
fun bindUsernameError(textInputLayout: TextInputLayout, errorInputUsername: Boolean) {
    val message = if (errorInputUsername) {
        textInputLayout.context.getString(R.string.error_input_username)
    } else {
        null
    }
    textInputLayout.error = message
}

@BindingAdapter("progressBarVisibility")
fun bindProgressBarVisibility(progressBar: ProgressBar, isVisible: Boolean) {
    if (isVisible) {
        progressBar.visibility = View.VISIBLE
    } else {
        progressBar.visibility = View.GONE
    }
}