package com.example.cozinhafacil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                FavoritosScreen()
            }
        }
    }
}

@Composable
fun FavoritosScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Favoritos", fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back action */ }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                },
                backgroundColor = Color.White,
                elevation = 4.dp
            )
        }
    ) {
        ListaReceitas()
    }
}

@Composable
fun ListaReceitas() {
    Column(modifier = Modifier.padding(16.dp)) {
        repeat(4) { index ->
            ReceitaItem(
                titulo = "Receita ${index + 1}",
                descricao = "Supporting line text lorem ipsum dolor sit amet, consectetur.",
                username = "Username"
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun ReceitaItem(titulo: String, descricao: String, username: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFFFF176)) // Amarelo claro
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(android.R.drawable.sym_def_app_icon), // Substitua por seu ícone/avatar
            contentDescription = "Avatar",
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = titulo, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = descricao, fontSize = 14.sp, color = Color.Gray)
        }
        IconButton(onClick = { /* Handle favorite action */ }) {
            Icon(Icons.Filled.Star, contentDescription = "Favoritar", tint = Color.Gray)
        }
    }
}
