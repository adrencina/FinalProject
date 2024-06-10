package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.example.finalproject.Utils.enable
import com.example.finalproject.Utils.visible
import com.example.finalproject.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
    private var isRegistrationClickable : Boolean = false
    private var isAtleast8 : Boolean = false
    private var hasUpperCase : Boolean = false
    private var hasNumber : Boolean = false
    private var hasSymbol : Boolean = false
    private var hasLowerCase : Boolean = false
    private var emailPattern : Boolean = false
    private var emailNotEmpty : Boolean = false


    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.cvLogIn.enable(false)
        binding.cvLogInError.enable(false)

        binding.checkBoxPassword.setOnClickListener {
            checkboxChecker()
        }

        binding.cvLogIn.setOnClickListener {
            emailCheck()
            passswordCheck()
            loginSucces() // change to Data Base later
        }
        binding.cvLogInError.setOnClickListener {
            emailCheck()
            passswordCheck()
            loginSucces() // change to Data Base later
        }
        inputChangePassWord()
        inputChangeEmail()
    }

    private fun emailCheck(){
        val email: String = binding.inputEmail.text.toString().trim()
        if (email.isNotEmpty()){
            emailNotEmpty = true
        } else {
            emailNotEmpty = false
            binding.inputEmail.error = "Email requerido, no puede estar vacio."
        }
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailPattern = true
        } else {
            emailPattern = false
            binding.inputEmail.error = "Email invalido, escriba un email correcto."
        }
        checkAllData()
    }
    private fun passswordCheck(){
        val password = binding.inputPassword.text.toString().trim()
        // for 8 characters
        if (password.length >= 8){
            isAtleast8 = true
            binding.cvLogIn.enable(true)
        } else {
            isAtleast8 = false
            binding.inputPassword.error = "Contraseña debil, es necesario minimo 8 caracteres."
        }
        // for uppercase
        if (Utils.PASSWORD_UPPERCASE_PATTERN.matcher(password).matches()) {
            hasUpperCase = true
        } else {
            hasUpperCase = false
            binding.inputPassword.error = "Contraseña Debil, se necesita minimo 1 letra mayuscula."
        }
        // for number
        if (Utils.PASSWORD_NUMBER_PATTERN.matcher(password).matches()) {
            hasNumber = true
        } else {
            hasNumber = false
            binding.inputPassword.error = "Contraseña Debil, se necesita minimo 1 numero."
        }
        // for lowercase
        if (Utils.PASSWORD_LOWERCASE_PATTERN.matcher(password).matches()) {
            hasLowerCase = true
        } else {
            hasLowerCase = false
            binding.inputPassword.error = "Contraseña Debil, se necesita minimo 1 letra minuscula."
        }
        // for symbols
        if (Utils.PASSWORD_SPECIAL_CHARACTER_PATTERN.matcher(password).matches()) {
            hasSymbol = true
        } else {
            hasSymbol = false
            binding.inputPassword.error = "Contraseña Debil, se necesita minimo 1 caracter especial."
        }
        checkAllData()
    }
    private fun checkAllData(){
        if (isAtleast8 && hasLowerCase && hasUpperCase && hasSymbol && hasNumber && emailPattern && emailNotEmpty){
            isRegistrationClickable = true
            loginButtonSwitcher(okA = true,okB = false)
        }else {
            isRegistrationClickable = false
            loginButtonSwitcher(okA = false,okB = true)
        }
    }
    private fun inputChangeEmail(){
        binding.inputEmail.addTextChangedListener {
            binding.emailErrorMessage.visible(false)
            emailCheck()
            loginButtonSwitcher(okA = true,okB = false)
        }
    }
    private fun inputChangePassWord(){
        binding.inputPassword.addTextChangedListener {
            binding.emailErrorMessage.visible(false)
            passswordCheck()
            loginButtonSwitcher(okA = true,okB = false)
        }
    }


    private fun loginSucces(){
        if (isRegistrationClickable){
            //call data base and go home on viewmodel
            binding.containerLoading.visible(true)
            val intent =  Intent(this, PreLoginActivity::class.java)
            startActivity(intent)
            finish()
        }else {
            binding.emailErrorMessage.visible(true)
        }
    }

    //change on viewmodel
    private fun loginButtonSwitcher(okA : Boolean,okB: Boolean){
        binding.cvLogIn.enable(okA)
        binding.cvLogIn.visible(okA)
        binding.cvLogInError.enable(okB)
        binding.cvLogInError.visible(okB)
    }
    private fun checkboxChecker(){
        if (binding.checkBoxPassword.isChecked){
            binding.inputPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
        }else {
            binding.inputPassword.transformationMethod = PasswordTransformationMethod.getInstance()
        }
    }
}
