package com.example.finalproject.ui.login.viewmodel

import android.app.Application
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.finalproject.data.service.dto.Utils.visible
import com.example.finalproject.data.dto.request.LoginRequest
import com.example.finalproject.data.dto.response.LoginResponse
import com.example.finalproject.data.repository.UserRepository
import com.example.finalproject.data.service.dto.LoginState
import com.example.finalproject.data.repository.TokenManager
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private companion object {
        var switch: Boolean = false
        var switcherA: Boolean = false
        var switcherB: Boolean = false
    }

    private val userRepo = UserRepository(application.applicationContext)
    val loginResult: MutableLiveData<LoginState<LoginResponse>> = MutableLiveData()

    // Fun iniciar sesión
    fun loginUser(email: String, password: String) {
        if (!isValidEmail(email) || !isValidPassword(password)) {
            loginResult.value = LoginState.Error("Email o contraseña inválida")
            return
        }
        loginResult.value = LoginState.Loading()
        viewModelScope.launch {
            try {
                val loginRequest = LoginRequest(
                    password = password,
                    email = email
                )
                val response = userRepo.loginUser(loginRequest = loginRequest)
                if (response.isSuccessful) {
                    // Guarda el token de acceso
                    response.body()?.accessToken?.let {
                        TokenManager.saveAuthToken(getApplication(), it)
                    }
                    loginResult.value = LoginState.Success(response.body())
                } else {
                    loginResult.value = LoginState.Error(response.message())
                }
            } catch (ex: Exception) {
                loginResult.value = LoginState.Error(ex.message)
            }
        }
    }

    // Valida email
    private fun isValidEmail(email: String): Boolean {
        val emailPattern = "^[A-Za-z0-9._%+-]+@[A-Za-z]+\\.[A-Za-z]{3}(\\.[A-Za-z]{2})?$".toRegex()
        return emailPattern.matches(email)
    }

    // Valida contraseña
    private fun isValidPassword(password: String): Boolean {
        val passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,20}$".toRegex()
        return passwordPattern.matches(password)
    }

    fun btnSwitcher(errorTextBox: TextView, btnOK: CardView, btnError: CardView) {
        switch = false
        if (!switch) {
            errorTextBox.visible(true)
            btnOK.visible(false)
            btnError.visible(true)
        } else {
            errorTextBox.visible(false)
            btnOK.visible(true)
            btnError.visible(false)
        }
    }

    fun checkBoxChecker(checkBox: CheckBox, passwordBox: TextInputEditText) {
        if (checkBox.isChecked) {
            passwordBox.transformationMethod = HideReturnsTransformationMethod.getInstance()
        } else {
            passwordBox.transformationMethod = PasswordTransformationMethod.getInstance()
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
            if (switch) {
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
            if (switch) {
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
}
