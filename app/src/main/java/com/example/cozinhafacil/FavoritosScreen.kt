package com.example.cozinhafacil

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritosScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Favoritos",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Handle search action */ }) {
                        Icon(Icons.Default.Search, contentDescription = "Buscar")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.Black
                )
            )
        },
        content = {
            ListaReceitas()
        }
    )
}

@Composable
fun ListaReceitas() {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp) // Espaçamento para iniciar mais abaixo
            .fillMaxSize()
    ) {
        item {
            Spacer(modifier = Modifier.height(65.dp))
        }

        items(4) { index ->
            ReceitaItem(
                titulo = "Receita ${index + 1}",
                descricao = "Uma breve descrição da receita com detalhes interessantes.",
                username = "Criado por: Usuário ${index + 1}"
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}


@Composable
fun ReceitaItem(titulo: String, descricao: String, username: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFFFF9C4))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avatar
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color(0xFFFFEB3B)), // Cor amarela
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Face,
                contentDescription = "Avatar",
                tint = Color(0xFFFFD54F),
                modifier = Modifier.size(28.dp)
            )
        }
        Spacer(modifier = Modifier.width(12.dp))

        // Informações da receita
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = titulo,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = descricao,
                fontSize = 14.sp,
                color = Color.Gray,
                maxLines = 2 // Limita o número de linhas
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = username,
                fontSize = 12.sp,
                color = Color.DarkGray
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Botão Favoritar
        IconButton(onClick = { /* Ação de favoritar */ }) {
            Icon(
                Icons.Filled.Star,
                contentDescription = "Favoritar",
                tint = Color(0xFFFFD54F),
                modifier = Modifier.size(28.dp)
            )
        }
    }
}
