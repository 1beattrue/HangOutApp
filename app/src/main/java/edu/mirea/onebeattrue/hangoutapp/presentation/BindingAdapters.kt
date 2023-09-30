package edu.mirea.onebeattrue.hangoutapp.presentation

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import edu.mirea.onebeattrue.hangoutapp.R

/**
 * fragment_register
 *
 * fragment_login
 */
@BindingAdapter("emailError")
fun bindEmailError(textInputLayout: TextInputLayout, isEmailError: Boolean) {
    val message = if (isEmailError) {
        textInputLayout.context.getString(R.string.error_input_email)
    } else {
        null
    }
    textInputLayout.error = message
}

@BindingAdapter("passwordError")
fun bindPasswordError(textInputLayout: TextInputLayout, isPasswordError: Boolean) {
    val message = if (isPasswordError) {
        textInputLayout.context.getString(R.string.error_input_password)
    } else {
        null
    }
    textInputLayout.error = message
}

@BindingAdapter("usernameError")
fun bindUsernameError(textInputLayout: TextInputLayout, isUsernameError: Boolean) {
    val message = if (isUsernameError) {
        textInputLayout.context.getString(R.string.error_input_username)
    } else {
        null
    }
    textInputLayout.error = message
}