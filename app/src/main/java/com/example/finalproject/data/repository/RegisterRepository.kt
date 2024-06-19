package com.example.finalproject.data.repository

import com.example.finalproject.data.dto.request.RegisterRequest
import com.example.finalproject.data.service.RegisterApiServisImp
import com.example.finalproject.data.service.dto.RegisterState
import java.net.SocketTimeoutException

class RegisterRepository(private val registerApiServiceImpl: RegisterApiServisImp) {

    suspend fun registerUser(request: RegisterRequest): RegisterState {
        return try {
            val response = registerApiServiceImpl.registerUser(request)
            if (response.isSuccessful) {
                val body = response.body()
                if (body?.accessToken != null) {
                    RegisterState.Success(body.accessToken)
                } else {
                    RegisterState.Error("Token vacío en la respuesta")
                }
            } else {
                RegisterState.Error("Error en la respuesta: ${response.message()}")
            }
        } catch (e: SocketTimeoutException) {
            RegisterState.Error("Timeout de conexión")
        } catch (e: Exception) {
            RegisterState.Error("Excepción: ${e.message}")
        }
    }
}