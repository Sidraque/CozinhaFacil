package com.example.cozinhafacil

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cozinhafacil.viewmodel.LoginViewModel
import com.example.cozinhafacil.viewmodel.LoginViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cozinhafacil.repository.AuthRepository

@Composable
fun LoginScreen(navController: NavController) {
    val loginViewModel: LoginViewModel = viewModel(factory = LoginViewModelFactory(AuthRepository()))

    var emailInput by remember { mutableStateOf("") }
    var senhaInput by remember { mutableStateOf("") }

    val isLoading by remember { mutableStateOf(loginViewModel.isLoading) }
    val errorMessage = loginViewModel.retrieveErrorMessage()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Texto "Seja bem-vindo!"
        Text(
            text = "Seja bem-vindo!",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Texto "Efetue seu login"
        Text(
            text = "Efetue seu login",
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Campo de email ou usuário
        OutlinedTextField(
            value = emailInput,
            onValueChange = { emailInput = it },
            label = { Text("E-mail ou usuário") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de senha
        OutlinedTextField(
            value = senhaInput,
            onValueChange = { senhaInput = it },
            label = { Text("Senha") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botão "Acessar"
        Button(
            onClick = {
                loginViewModel.login(emailInput, senhaInput, onSuccess = {
                    navController.navigate("home")
                })
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFEB3B)),
            modifier = Modifier
                .width(400.dp)
                .height(50.dp)
        ) {
            Text("Acessar", color = Color.Black, fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Exibe a mensagem de erro, se houver
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(0.dp))

        // Botão "Cadastre-se"
        Button(
            onClick = { navController.navigate("cadastro") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFEB3B)),
            modifier = Modifier
                .width(400.dp)
                .height(50.dp)
        ) {
            Text("Cadastre-se", color = Color.Black, fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Link "Esqueceu sua senha?"
        val annotatedLinkString = buildAnnotatedString {
            append("Esqueceu sua senha? ")
            pushStyle(SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold))
            append("Clique aqui")
            pop()
        }

        ClickableText(
            text = annotatedLinkString,
            onClick = { /* Implementação do clique */ },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = TextStyle(fontSize = 16.sp)
        )
    }
}
