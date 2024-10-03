package com.example.cozinhafacil

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

data class Receita(
    val titulo: String,
    val descricao: String,
    val autor: String,
    var favoritado: MutableState<Boolean> = mutableStateOf(false)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    val receitas = remember {
        mutableStateListOf(
            Receita("Receita 1", "Supporting line text lorem ipsum dolor sit amet, consectetur.", "Username"),
            Receita("Receita 2", "Supporting line text lorem ipsum dolor sit amet, consectetur.", "Username"),
            Receita("Receita 3", "Supporting line text lorem ipsum dolor sit amet, consectetur.", "Username")
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Home",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFFFD600)), // Tom mais escuro de amarelo para destaque
                actions = {
                    IconButton(onClick = { /* Navegar para o perfil do usuário */ }) {
                        Icon(Icons.Default.Person, contentDescription = "Perfil", tint = Color.Black)
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFFFFD600), // Tom mais claro de amarelo para a barra inferior
                content = {
                    IconButton(onClick = { /* Navegar para pesquisa */ }) {
                        Icon(Icons.Default.Search, contentDescription = "Pesquisar", tint = Color.Black)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    FloatingActionButton(
                        onClick = { /* Navegar para adicionar nova receita */ },
                        containerColor = Color(0xFFFFF59D), // Amarelo mais claro para destacar o botão de adicionar
                        contentColor = Color.Black,
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Adicionar Receita")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // Lista de Receitas
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(receitas) { receita ->
                    ReceitaItem(receita)
                }
            }
        }
    }
}

@Composable
fun ReceitaItem(receita: Receita) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color(0xFFFFF59D), shape = RoundedCornerShape(16.dp)) // Fundo de amarelo claro para o item da receita
            .padding(16.dp),
        verticalAlignment = Alignment.Top
    ) {
        // Ícone de usuário à esquerda da receita
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(end = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(Color(0xFFFFEB3B), shape = CircleShape), // Tom de amarelo padrão para o ícone do autor
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Person, contentDescription = "Autor", tint = Color.Gray, modifier = Modifier.size(24.dp))
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Nome do autor
            Text(
                text = receita.autor,
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Detalhes da Receita
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = receita.titulo, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = receita.descricao, fontSize = 14.sp, color = Color.Black)
        }

        // Ícone de favoritar/desfavoritar
        Icon(
            imageVector = if (receita.favoritado.value) Icons.Filled.Star else Icons.Outlined.Star,
            contentDescription = if (receita.favoritado.value) "Desfavoritar" else "Favoritar",
            tint = if (receita.favoritado.value) Color(0xFFFFD700) else Color.Gray, // Amarelo mais brilhante para favoritado
            modifier = Modifier
                .size(32.dp)
                .clickable { receita.favoritado.value = !receita.favoritado.value }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}
