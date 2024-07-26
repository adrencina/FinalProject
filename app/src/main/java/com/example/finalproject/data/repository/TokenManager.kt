package com.example.finalproject.data.repository

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.finalproject.R

object TokenManager {

    private const val USER_TOKEN = "User_Token"
    private var user_token = ""

    // Obtiene el token almacenado en memoria o SharedPreferences
    fun getToken(context: Context): String {

        if (user_token.isEmpty()) {
            user_token = getString(context, USER_TOKEN) ?: ""
        }
        return user_token

         //return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBcGkgVXNlcnMgU3ViamVjdCIsInJvbGUiOjEsImlzcyI6IkFwaSBVc2VycyIsInVzZXJJZCI6MSwiaWF0IjoxNzIxOTY4MTA3LCJqdGkiOiIwMjA0ZjQxMi04YWFjLTQxNjQtYmRkZC0xZWVkYzJkMTRkMzIifQ.EYUraZhIzphOQ1iXKYwIAPSJLxo0NZh0uUN5DDNR0LA"
    }

    // Guarda el token en memoria y SharedPreferences
    fun saveAuthToken(context: Context, token: String) {
        user_token = token
        Log.d("TokenManager", "Token: $token")
        saveString(context, USER_TOKEN, token)
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
        user_token = ""
        val editor = context.getSharedPreferences(
            context.getString(R.string.app_name), Context.MODE_PRIVATE
        ).edit()
        editor.clear()
        editor.apply()
    }
}