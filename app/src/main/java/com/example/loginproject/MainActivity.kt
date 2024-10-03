package com.example.cozinhafacil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


// Variáveis globais para armazenar dados do usuário
var nome by mutableStateOf("")
var email by mutableStateOf("")
var telefone by mutableStateOf("")
var hobby by mutableStateOf("")
var senha by mutableStateOf("")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginApp()
        }
    }
}

@Composable
fun LoginApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable("login") { LoginScreen(navController) }
        composable("cadastro") { CadastroScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("editar") { EditScreen(navController) }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoginApp()
}
