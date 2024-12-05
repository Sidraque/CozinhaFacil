package com.example.cozinhafacil.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cozinhafacil.repository.AuthRepository
import kotlinx.coroutines.launch

class CadastroViewModel(private val authRepository: AuthRepository) : ViewModel() {

    var isLoading by mutableStateOf(false)  // Estado de carregamento
    var cadastroResult: String? = null      // Mensagem de sucesso ou erro

    fun cadastrarUsuario(
        email: String,
        password: String,
        nome: String,
        sobrenome: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        // Define o estado de carregamento como verdadeiro antes de iniciar o cadastro
        isLoading = true

        viewModelScope.launch {
            try {
                val result = authRepository.registerUser(email, password, nome, sobrenome)
                cadastroResult = "Cadastro realizado com sucesso!"
                onSuccess()  // Chama o callback de sucesso
            } catch (e: Exception) {
                cadastroResult = e.message  // Armazena o erro
                onFailure(e.message ?: "Erro desconhecido")
            } finally {
                isLoading = false  // Define o estado de carregamento como falso após a conclusão
            }
        }
    }
}
