package com.example.cozinhafacil

import PerfilScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cozinhafacil.repository.AuthRepository
import com.example.cozinhafacil.screens.* // Importa todas as telas do pacote de forma limpa
import com.google.firebase.auth.FirebaseAuth

// Variáveis globais para armazenar dados do usuário
var nome by mutableStateOf("")
var email by mutableStateOf("")
var telefone by mutableStateOf("")
var hobby by mutableStateOf("")
var senha by mutableStateOf("")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Repositório de autenticação
        val authRepository = AuthRepository()

        // Verificar se o usuário já está logado ao iniciar o app
        val user = FirebaseAuth.getInstance().currentUser
        val startDestination = if (user != null) "home" else "login"

        setContent {
            LoginApp(startDestination = startDestination, authRepository = authRepository)
        }
    }
}

@Composable
fun LoginApp(startDestination: String, authRepository: AuthRepository) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = startDestination) {
        composable("login") { LoginScreen(navController) }
        composable("cadastro") { CadastroScreen(navController) }
        composable("home") { HomeScreen(navController, authRepository) }
        composable("editar") { EditScreen(navController) }
        composable("perfil") { PerfilScreen(navController) }
        composable("editarPerfil") { EditarPerfilScreen(navController) }
        composable("favoritos") { FavoritosScreen(navController) }
        composable("receita") { ReceitaScreen(navController) }
        composable("criarReceita") { CriarReceitaScreen(navController, authRepository) }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val authRepository = AuthRepository()
    LoginApp(startDestination = "login", authRepository = authRepository)
}
