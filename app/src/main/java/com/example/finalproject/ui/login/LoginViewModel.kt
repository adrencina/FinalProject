package com.example.finalproject.ui.login

import android.app.Application
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.finalproject.Data.Dto.Request.LoginRequest
import com.example.finalproject.Data.Dto.Response.LoginResponse
import com.example.finalproject.Data.Repostory.UserRepository
import com.example.finalproject.Data.Service.dto.LoginState
import com.example.finalproject.Utils
import com.example.finalproject.Utils.visible
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private companion object {
        const val MIN_PASSWORD_LENGTH = 1
        const val MAX_PASSWORD_LENGHT = 20
        var isValidPasswordCheck: Boolean = false
        var hasUpperCase: Boolean = false
        var hasLowerCase: Boolean = false
        var hasNumber: Boolean = false
        var hasSymbol: Boolean = false
        var switch: Boolean = false
        var switcherA: Boolean = false
        var switcherB: Boolean = false
    }

    private val userRepo = UserRepository()
    val loginResult: MutableLiveData<LoginState<LoginResponse>> = MutableLiveData()
    fun loginUser(email: String, password: String) {
        loginResult.value = LoginState.Loading()
        viewModelScope.launch {
            try {
                val loginRequest = LoginRequest(
                    password = password,
                    email = email
                )
                val response = userRepo.loginUser(loginRequest = loginRequest)
                if (isValidEmail(email) && isValidPassword(password)) {
                    loginResult.value = LoginState.Success(response?.body())
                } else {
                    loginResult.value = LoginState.Error(response?.message())
                }
            } catch (ex: Exception) {
                loginResult.value = LoginState.Error(ex.message)
            }
        }
    }

    private fun isValidEmail(email: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotEmpty()
    private fun isValidPassword(password: String): Boolean =
        password.length in MIN_PASSWORD_LENGTH..MAX_PASSWORD_LENGHT && password.isNotEmpty() && isValidPasswordCheck
    fun passwordChecker() {
        isValidPasswordCheck = hasSymbol && hasNumber && hasUpperCase && hasLowerCase
    }
    fun passwordRegexValidation(password: String) {
//         for uppercase
        hasUpperCase = Utils.PASSWORD_UPPERCASE_PATTERN.matcher(password).matches()
        // for number
        hasNumber = Utils.PASSWORD_NUMBER_PATTERN.matcher(password).matches()
        // for lowercase
        hasLowerCase = Utils.PASSWORD_LOWERCASE_PATTERN.matcher(password).matches()
        // for symbols
        hasSymbol = Utils.PASSWORD_SPECIAL_CHARACTER_PATTERN.matcher(password).matches()
    }
    fun btnSwitcher(errorTextBox: TextView, btnOK: CardView, btnError: CardView) {
        switch = false
        if (switch == false) {
            errorTextBox.visible(true)
            btnOK.visible(false)
            btnError.visible(true)
        } else {
            errorTextBox.visible(false)
            btnOK.visible(true)
            btnError.visible(false)
        }
    }
    fun emailListenerChange(
        errorTextBox: TextView,
        emailBox: TextInputEditText,
        btnOK: CardView,
        btnError: CardView
    ) {
        emailBox.addTextChangedListener {
            switch = true
            if (switch == true) {
                switcherA = true
                switcherB = false
                errorTextBox.visible(switcherB)
                btnOK.visible(switcherA)
                btnError.visible(switcherB)
            } else {
                switcherA = false
                switcherB = true
                errorTextBox.visible(switcherB)
                btnOK.visible(switcherA)
                btnError.visible(switcherB)
            }
        }
    }
    fun passwordListenerChange(
        errorTextBox: TextView,
        passwordBox: TextInputEditText,
        btnOK: CardView,
        btnError: CardView
    ) {
        passwordBox.addTextChangedListener {
            switch = true
            if (switch == true) {
                switcherA = true
                switcherB = false
                errorTextBox.visible(switcherB)
                btnOK.visible(switcherA)
                btnError.visible(switcherB)
            } else {
                switcherA = false
                switcherB = true
                errorTextBox.visible(switcherB)
                btnOK.visible(switcherA)
                btnError.visible(switcherB)
            }
        }
    }
    fun checkBoxChecker(checkBox: CheckBox, passwordBox: TextInputEditText) {
        if (checkBox.isChecked) {
            passwordBox.transformationMethod = HideReturnsTransformationMethod.getInstance()
        } else {
            passwordBox.transformationMethod = PasswordTransformationMethod.getInstance()
        }
    }
}