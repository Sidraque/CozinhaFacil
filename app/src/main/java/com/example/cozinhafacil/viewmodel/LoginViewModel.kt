package com.example.cozinhafacil.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cozinhafacil.repository.AuthRepository
import kotlinx.coroutines.launch
import com.google.firebase.auth.FirebaseAuthException

class LoginViewModel(private val authRepository: AuthRepository) : ViewModel() {

    var isLoading by mutableStateOf(false)
    var loginResult by mutableStateOf<LoginResult?>(null)
    var errorMessage by mutableStateOf("")

    // Função para realizar o login
    fun login(email: String, senha: String, onSuccess: () -> Unit) {
        if (isLoading) return

        isLoading = true
        viewModelScope.launch {
            try {
                // Realiza a autenticação usando o repositório Firebase
                val result = authRepository.login(email, senha)

                if (result.isSuccessful) {
                    loginResult = LoginResult.Success
                    onSuccess()  // Chama a navegação para a tela principal
                } else {
                    // Verificar os erros específicos do Firebase
                    val error = result.message ?: "Erro desconhecido"
                    loginResult = LoginResult.Failure(error)
                    errorMessage = when {
                        error.contains("INVALID_EMAIL") -> "E-mail inválido"
                        error.contains("WRONG_PASSWORD") -> "Senha incorreta"
                        error.contains("EMAIL_NOT_FOUND") -> "E-mail não encontrado"
                        error.contains("BAD_FORMAT") -> "O formato do e-mail está errado"
                        else -> "Credenciais incorretas ou malformadas"
                    }
                }
            } catch (e: FirebaseAuthException) {
                loginResult = LoginResult.Failure(e.message ?: "Erro de conexão")
                errorMessage = when {
                    e.message?.contains("user not found") == true -> "E-mail não encontrado"
                    e.message?.contains("invalid email") == true -> "E-mail inválido"
                    e.message?.contains("wrong password") == true -> "Senha incorreta"
                    e.message?.contains("badly formatted") == true -> "O formato do e-mail está errado"
                    else -> "Credenciais incorretas ou malformadas"
                }
            } catch (e: Exception) {
                loginResult = LoginResult.Failure(e.message ?: "Erro de conexão")
                errorMessage = e.message ?: "Erro de conexão"
            } finally {
                isLoading = false
            }
        }
    }

    // Função para retornar a mensagem de erro
    fun retrieveErrorMessage() = errorMessage
}

sealed class LoginResult {
    object Success : LoginResult()
    data class Failure(val message: String) : LoginResult()
}
