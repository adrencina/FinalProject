package com.example.finalproject.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.finalproject.R

object TokenManager {

    private const val USER_TOKEN = "User_Token"

    // Guarda el token
    fun saveAuthToken(context: Context, token: String) {
        saveString(context, USER_TOKEN, token)
    }

    // Obtiene el token
    fun getToken(context: Context): String? {
        return getString(context, USER_TOKEN)
    }

    // Guarda un string en SharedPreferences
    private fun saveString(context: Context, key: String, value: String) {
        val prefs: SharedPreferences = context.getSharedPreferences(
            context.getString(R.string.app_name), Context.MODE_PRIVATE
        )
        val editor = prefs.edit()
        editor.putString(key, value)
        editor.apply()
    }

    // Obtiene un string de SharedPreferences
    private fun getString(context: Context, key: String): String? {
        val prefs: SharedPreferences = context.getSharedPreferences(
            context.getString(R.string.app_name), Context.MODE_PRIVATE
        )
        return prefs.getString(key, null)
    }

    // Elimina todos los datos almacenados en SharedPreferences
    fun clearData(context: Context) {
        val editor = context.getSharedPreferences(
            context.getString(R.string.app_name), Context.MODE_PRIVATE
        ).edit()
        editor.clear()
        editor.apply()
    }
}