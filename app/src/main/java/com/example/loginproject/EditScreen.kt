package com.example.cozinhafacil

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun EditScreen(navController: NavController) {
    var nomeInput by remember { mutableStateOf(nome) }
    var emailInput by remember { mutableStateOf(email) }
    var telefoneInput by remember { mutableStateOf(telefone) }
    var hobbyInput by remember { mutableStateOf(hobby) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Editar Dados", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        // Campo Nome
        TextField(
            value = nomeInput,
            onValueChange = { nomeInput = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo Email
        TextField(
            value = emailInput,
            onValueChange = { emailInput = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo Telefone
        TextField(
                    value = telefoneInput,
            onValueChange = { telefoneInput = it },
            label = { Text("Telefone") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo Hobby
        TextField(
            value = hobbyInput,
            onValueChange = { hobbyInput = it },
            label = { Text("Hobby") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            // Atualiza os valores globais com os novos dados inseridos pelo usuário
            nome = nomeInput
            email = emailInput
            telefone = telefoneInput
            hobby = hobbyInput

            // Retorna para a tela Home
            navController.navigate("home")
        }) {
            Text("Salvar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditScreenPreview() {
    EditScreen(rememberNavController())
}
