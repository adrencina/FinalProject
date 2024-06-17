package com.example.finalproject.data.repository

import com.example.finalproject.data.dto.request.RegisterRequest
import com.example.finalproject.data.service.RegisterApiServisImp
import com.example.finalproject.data.service.dto.RegisterState
import retrofit2.HttpException
import java.io.IOException

class RegisterRepository (private val registerApiServiceImpl: RegisterApiServisImp) {
    suspend fun registerUser(request: RegisterRequest): RegisterState {
        return try {
            val response = registerApiServiceImpl.registerUser(request)
            if (response.isSuccessful) {
                response.body()?.let {
                    RegisterState.Success(it)
                } ?: RegisterState.Error("Error al registrar usuario: respuesta vacía")
            } else {
                RegisterState.Error("Error al registrar usuario: ${response.message()}")
            }
        } catch (e: HttpException) {
            RegisterState.Error("Error al registrar usuario: ${e.message}")
        } catch (e: IOException) {
            RegisterState.Error("Error al registrar usuario: ${e.message}")
        }
    }
}