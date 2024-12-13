package com.example.cozinhafacil.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cozinhafacil.models.User
import com.example.cozinhafacil.repository.AuthRepository
import kotlinx.coroutines.launch

class CadastroViewModel(private val authRepository: AuthRepository) : ViewModel() {

    var isLoading by mutableStateOf(false)
    var cadastroResult by mutableStateOf<String?>(null)

    fun cadastrarUsuario(user: User, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        if (isLoading) return

        isLoading = true
        viewModelScope.launch {
            try {
                val result = authRepository.registerUser(user, password)
                if (result.isSuccess) {
                    cadastroResult = "Cadastro realizado com sucesso!"
                    onSuccess()
                } else {
                    val error = result.exceptionOrNull()?.message ?: "Erro desconhecido"
                    cadastroResult = error
                    onFailure(error)
                }
            } catch (e: Exception) {
                val error = e.message ?: "Erro desconhecido"
                cadastroResult = error
                onFailure(error)
            } finally {
                isLoading = false
            }
        }
    }
}
