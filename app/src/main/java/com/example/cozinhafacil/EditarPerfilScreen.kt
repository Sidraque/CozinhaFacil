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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.database.*

@Composable
fun EditarPerfilScreen(navController: NavController) {
    // Variáveis de estado para entrada do usuário
    var primeiroNome by remember { mutableStateOf("") }
    var segundoNome by remember { mutableStateOf("") }
    var senhaAtualizada by remember { mutableStateOf("") }
    var senhaAtual by remember { mutableStateOf("") }
    var mensagem by remember { mutableStateOf("") }

    // Carregar dados do Firebase ao abrir a tela
    LaunchedEffect(Unit) {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            val databaseReference = FirebaseDatabase.getInstance().getReference("users/${user.uid}")
            databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    primeiroNome = snapshot.child("nome").getValue(String::class.java) ?: ""
                    segundoNome = snapshot.child("sobrenome").getValue(String::class.java) ?: ""
                }

                override fun onCancelled(error: DatabaseError) {
                    mensagem = "Erro ao carregar dados: ${error.message}"
                }
            })
        } else {
            mensagem = "Usuário não logado."
        }
    }

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
                Icon(
                    Icons.Default.Edit,
                    contentDescription = "Editar Avatar",
                    tint = Color.Black,
                    modifier = Modifier.size(40.dp)
                )
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
            label = { Text("Primeiro Nome") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = segundoNome,
            onValueChange = { segundoNome = it },
            label = { Text("Sobrenome") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de senha atual
        OutlinedTextField(
            value = senhaAtual,
            onValueChange = { senhaAtual = it },
            label = { Text("Senha Atual") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = senhaAtualizada,
            onValueChange = { senhaAtualizada = it },
            label = { Text("Nova Senha") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val user = FirebaseAuth.getInstance().currentUser
                if (user != null) {
                    // Atualiza nome e sobrenome no Firebase sem necessidade de reautenticação
                    if (primeiroNome.isNotEmpty() || segundoNome.isNotEmpty()) {
                        val databaseReference =
                            FirebaseDatabase.getInstance().getReference("users/${user.uid}")
                        val updates = mutableMapOf<String, Any>()
                        if (primeiroNome.isNotEmpty()) updates["nome"] = primeiroNome
                        if (segundoNome.isNotEmpty()) updates["sobrenome"] = segundoNome
                        databaseReference.updateChildren(updates)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    mensagem = "Nome e sobrenome atualizados com sucesso!"
                                } else {
                                    mensagem = "Erro ao atualizar dados: ${task.exception?.message}"
                                }
                            }
                    }

                    // Atualiza senha no Firebase, se fornecida
                    if (senhaAtualizada.isNotEmpty()) {
                        val credential = EmailAuthProvider.getCredential(user.email!!, senhaAtual)
                        user.reauthenticate(credential).addOnCompleteListener { reauthTask ->
                            if (reauthTask.isSuccessful) {
                                user.updatePassword(senhaAtualizada)
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            mensagem = "Senha atualizada com sucesso!"
                                        } else {
                                            mensagem = "Erro ao atualizar a senha: ${task.exception?.message}"
                                        }
                                    }
                            } else {
                                mensagem = "Erro ao reautenticar: ${reauthTask.exception?.message}"
                            }
                        }
                    }
                } else {
                    mensagem = "Usuário não logado."
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFEB3B)),
            modifier = Modifier
                .height(50.dp)
                .width(400.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Salvar", color = Color.Black, fontSize = 18.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(Icons.Default.ArrowForward, contentDescription = null, tint = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Botão Voltar
        Button(
            onClick = { navController.navigateUp() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFEB3B)),
            modifier = Modifier
                .height(50.dp)
                .width(400.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.Black)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Voltar", color = Color.Black, fontSize = 18.sp)
            }
        }

        Text(
            text = mensagem,
            color = if (mensagem.contains("sucesso")) Color.Green else Color.Red,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}
