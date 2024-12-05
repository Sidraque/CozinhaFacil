package com.example.cozinhafacil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun EditarPerfilScreen(navController: NavController) {
    // Variáveis de estado para entrada do usuário
    var primeiroNome by remember { mutableStateOf("") }
    var segundoNome by remember { mutableStateOf("") }
    var senhaAtualizada by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Avatar com botão de edição
        Spacer(modifier = Modifier.height(32.dp))

        Box(contentAlignment = Alignment.BottomEnd) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color(0xFFFFF9C4), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Edit, contentDescription = "Editar Avatar", tint = Color.Black, modifier = Modifier.size(40.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Editar",
            fontSize = 24.sp,
            color = Color.Black,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Campos de entrada de texto
        OutlinedTextField(
            value = primeiroNome,
            onValueChange = { primeiroNome = it },
            label = { Text("Primeiro nome") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = segundoNome,
            onValueChange = { segundoNome = it },
            label = { Text("Segundo nome") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = senhaAtualizada,
            onValueChange = { senhaAtualizada = it },
            label = { Text("Senha") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botão Salvar
        Button(
            onClick = {
                // Lógica para salvar os dados editados
                navController.navigateUp()
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFEB3B)),
            modifier = Modifier
                .width(200.dp),
            shape = RoundedCornerShape(13.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Salvar", color = Color.Black, fontSize = 18.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(Icons.Default.ArrowForward, contentDescription = null, tint = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(1.dp))

        // Botão Voltar
        Button(
            onClick = { navController.navigateUp() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFEB3B)),
            modifier = Modifier
                .width(200.dp),
            shape = RoundedCornerShape(13.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.Black)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Voltar", color = Color.Black, fontSize = 18.sp)
            }
        }
    }
}
