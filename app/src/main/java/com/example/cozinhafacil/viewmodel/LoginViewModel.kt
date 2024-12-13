package com.example.cozinhafacil.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cozinhafacil.models.User
import com.example.cozinhafacil.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val authRepository: AuthRepository) : ViewModel() {

    var isLoading by mutableStateOf(false)
    var loginResult by mutableStateOf<LoginResult?>(null)

    fun login(email: String, password: String, onSuccess: (User) -> Unit, onFailure: (String) -> Unit) {
        if (isLoading) return

        isLoading = true
        viewModelScope.launch {
            try {
                val result = authRepository.login(email, password)
                if (result.isSuccess) {
                    val user = result.getOrNull()!!
                    loginResult = LoginResult.Success(user)
                    onSuccess(user)
                } else {
                    val error = result.exceptionOrNull()?.message ?: "Erro desconhecido"
                    loginResult = LoginResult.Failure(error)
                    onFailure(error)
                }
            } catch (e: Exception) {
                val error = e.message ?: "Erro desconhecido"
                loginResult = LoginResult.Failure(error)
                onFailure(error)
            } finally {
                isLoading = false
            }
        }
    }
}

sealed class LoginResult {
    data class Success(val user: User) : LoginResult()
    data class Failure(val message: String) : LoginResult()
}
