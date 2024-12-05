package com.example.cozinhafacil

import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cozinhafacil.repository.AuthRepository
import com.example.cozinhafacil.viewmodel.CadastroViewModel
import com.example.cozinhafacil.viewmodel.CadastroViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadastroScreen(
    navController: NavController,
    viewModel: CadastroViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = CadastroViewModelFactory(AuthRepository())
    )
) {
    var nomeInput by remember { mutableStateOf("") }
    var sobrenomeInput by remember { mutableStateOf("") }
    var emailInput by remember { mutableStateOf("") }
    var senhaInput by remember { mutableStateOf("") }
    var confirmarSenhaInput by remember { mutableStateOf("") }

    // Estados para erros de validação
    var nomeError by remember { mutableStateOf(false) }
    var sobrenomeError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var senhaError by remember { mutableStateOf(false) }
    var confirmarSenhaError by remember { mutableStateOf(false) }

    // Carregamento e mensagem de sucesso ou erro
    val isLoading = viewModel.isLoading
    val cadastroResult = viewModel.cadastroResult

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título da tela
        Text(
            "Cadastro",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Campo Nome
        OutlinedTextField(
            value = nomeInput,
            onValueChange = {
                nomeInput = it
                nomeError = nomeInput.isEmpty()
            },
            label = {
                Text("Primeiro Nome", color = if (nomeError) Color.Red else Color.Gray)
            },
            isError = nomeError,
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.White,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                focusedLabelColor = Color.Gray // Cor cinza quando focado
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            singleLine = true
        )
        if (nomeError) {
            Text("Nome é obrigatório", color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Campo Sobrenome
        OutlinedTextField(
            value = sobrenomeInput,
            onValueChange = {
                sobrenomeInput = it
                sobrenomeError = sobrenomeInput.isEmpty()
            },
            label = {
                Text("Sobrenome", color = if (sobrenomeError) Color.Red else Color.Gray)
            },
            isError = sobrenomeError,
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.White,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                focusedLabelColor = Color.Gray // Cor cinza quando focado
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            singleLine = true
        )
        if (sobrenomeError) {
            Text("Sobrenome é obrigatório", color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Campo Email
        OutlinedTextField(
            value = emailInput,
            onValueChange = {
                emailInput = it
                emailError = emailInput.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()
            },
            label = {
                Text("E-mail", color = if (emailError) Color.Red else Color.Gray)
            },
            isError = emailError,
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.White,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                focusedLabelColor = Color.Gray // Cor cinza quando focado
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            singleLine = true
        )
        if (emailError) {
            Text("E-mail inválido", color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Campo Senha
        OutlinedTextField(
            value = senhaInput,
            onValueChange = {
                senhaInput = it
                senhaError = senhaInput.length < 6
            },
            label = {
                Text("Senha", color = if (senhaError) Color.Red else Color.Gray)
            },
            isError = senhaError,
            modifier = Modifier
                .fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.White,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                focusedLabelColor = Color.Gray // Cor cinza quando focado
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            singleLine = true
        )
        if (senhaError) {
            Text("Senha deve ter no mínimo 6 caracteres", color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Campo Confirmar Senha
        OutlinedTextField(
            value = confirmarSenhaInput,
            onValueChange = {
                confirmarSenhaInput = it
                confirmarSenhaError = confirmarSenhaInput != senhaInput
            },
            label = {
                Text("Confirmar Senha", color = if (confirmarSenhaError) Color.Red else Color.Gray)
            },
            isError = confirmarSenhaError,
            modifier = Modifier
                .fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.White,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                focusedLabelColor = Color.Gray
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            singleLine = true
        )
        if (confirmarSenhaError) {
            Text("As senhas não coincidem", color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botão de Cadastro
        Button(
            onClick = {
                if (!nomeError && !sobrenomeError && !emailError && !senhaError && !confirmarSenhaError) {
                    // Iniciar o processo de cadastro
                    viewModel.cadastrarUsuario(
                        emailInput,
                        senhaInput,
                        nomeInput,
                        sobrenomeInput,
                        onSuccess = {
                            navController.navigate("login")  // Substitua com o nome da sua tela de login
                        },
                        onFailure = { errorMessage ->
                        }
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFEB3B)),
            shape = RoundedCornerShape(50)
        ) {
            Text("Cadastre-se", fontSize = 18.sp, color = Color.Black)
        }

        // Carregamento (loading)
        if (isLoading) {
            Spacer(modifier = Modifier.height(24.dp))
            CircularProgressIndicator(color = Color.Blue)
        }

        // Mensagem de sucesso ou erro
        cadastroResult?.let {
            Spacer(modifier = Modifier.height(24.dp))
            Text(it, color = if (it.contains("sucesso")) Color.Green else Color.Red, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Link para a tela de login
        TextButton(onClick = { navController.navigate("login") }) {
            Text("Login", color = Color.Gray)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CadastroScreenPreview() {
    CadastroScreen(rememberNavController())
}
