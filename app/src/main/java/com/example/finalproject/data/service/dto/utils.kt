package com.example.finalproject.data.service.dto

import android.util.Log
import android.view.View
import java.util.regex.Pattern

object Utils {

    val PASSWORD_UPPERCASE_PATTERN: Pattern = Pattern.compile("(?=.*[A-Z])" + ".*")

    val PASSWORD_LOWERCASE_PATTERN: Pattern = Pattern.compile("(?=.*[a-z])" + ".*")

    val PASSWORD_NUMBER_PATTERN: Pattern = Pattern.compile("(?=.*[0-9])" + ".*")

    val PASSWORD_SPECIAL_CHARACTER_PATTERN: Pattern = Pattern.compile("(?=.*[@#$%^&+=])" + ".*")

    var ID_PRODUCT: Int? = 0

    fun View.visible(isVisible: Boolean) {
        Log.d(
            "Utils Chequeo",
            "Setting visibility to ${if (isVisible) "VISIBLE" else "GONE"} for view with id: $id"
        )
        visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    fun View.enable(enabled: Boolean) {
        isEnabled = enabled
        alpha = if (enabled) 1f else 0.5f
    }
}