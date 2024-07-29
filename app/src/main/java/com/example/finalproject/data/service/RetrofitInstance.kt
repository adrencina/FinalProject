package com.example.finalproject.data.service

import android.content.Context
import com.example.finalproject.data.repository.TokenManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    // URL base autenticación Registro/Login
    private const val BASE_URL_AUTH = "https://api-users-c9xg.onrender.com"

    // URL base para los endpoints generales
    private const val BASE_URL_GENERAL = "https://api-products-fe4p.onrender.com"

    // URL base para los endpoints de payment
    private const val BASE_URL_PAYMENT = "https://api-payments-1ztc.onrender.com/"


    private const val BASE_URL_COMMENTS = "https://api-comments-3yzc.onrender.com/"


    // Cliente HTTP con interceptor para logs
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // OkHttpClient con interceptor de autorización
    private fun getClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .addInterceptor(loggingInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS) // Tiempo conexión
            .readTimeout(60, TimeUnit.SECONDS)    // Tiempo lectura
            .writeTimeout(60, TimeUnit.SECONDS)   // Tiempo escritura
            .build()
    }

    // Retrofit para los endpoints generales
    fun getGeneralRetrofit(context: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_GENERAL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient(context))
            .build()
    }

    // Retrofit para los endpoints de payment
    fun getPaymentRetrofit(context: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_PAYMENT)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient(context))
            .build()
    }

    // Retrofit para los endpoints de comments
    fun getCommentsRetrofit(context: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_COMMENTS)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient(context))
            .build()
    }

    // Retrofit para los endpoints de autenticación
    fun getAuthRetrofit(context: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_AUTH)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient(context))
            .build()
    }
}

// Interceptor para agregar el token de autorización a las solicitudes
class AuthInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = TokenManager.getToken(context) ?: ""
        val originalRequest: Request = chain.request()
        val requestWithToken: Request = originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        return chain.proceed(requestWithToken)
    }
}