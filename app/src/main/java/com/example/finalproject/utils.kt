package com.example.finalproject

import android.view.View
import java.util.regex.Pattern

object Utils {
//    val PASSWORD_PATTERN: Pattern = Pattern.compile(
//        "^"
//                + "(?=.*[0-9])" //minimum one number
//                + "(?=.*[a-z])" //minimum one lower case character
//                + "(?=.*[A-Z])" //minimum one UPPER case character
//                + "(?=.*[a-zA-Z])" //any character
//                + "(?=.*[@#$%^&+=])" //minimum one special character
//                + "(?=\\S+$)" //no white spaces
//                + ".{6,}" //minimum length 6 characters
//                + "$"
//    )

    val PASSWORD_UPPERCASE_PATTERN: Pattern = Pattern.compile("(?=.*[A-Z])" + ".*")

    val PASSWORD_LOWERCASE_PATTERN: Pattern = Pattern.compile("(?=.*[a-z])" + ".*")

    val PASSWORD_NUMBER_PATTERN: Pattern = Pattern.compile("(?=.*[0-9])" + ".*")

    val PASSWORD_SPECIAL_CHARACTER_PATTERN: Pattern = Pattern.compile("(?=.*[@#$%^&+=])" + ".*")


    fun View.visible(isVisible :Boolean){
        visibility = if(isVisible) View.VISIBLE else View.GONE
    }
    fun View.enable(enabled:Boolean){
        isEnabled = enabled
        alpha = if (enabled) 1f else 0.5f
    }
}