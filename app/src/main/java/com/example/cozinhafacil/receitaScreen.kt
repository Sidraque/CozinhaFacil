package com.example.cozinhafacil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
                ReceitaDetalhesScreen()
            }
        }
    }
}

@Composable
fun ReceitaDetalhesScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Receita 1", fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back action */ }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Handle favorite action */ }) {
                        Icon(Icons.Filled.Star, contentDescription = "Favoritar")
                    }
                },
                backgroundColor = Color.White,
                elevation = 4.dp
            )
        }
    ) {
        ReceitaConteudo()
    }
}

@Composable
fun ReceitaConteudo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Header com Avatar e Username
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(android.R.drawable.sym_def_app_icon), // Substitua pelo seu ícone/avatar
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Username", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Imagens da receita
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            repeat(3) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(android.R.drawable.ic_menu_gallery), // Substitua pela imagem
                        contentDescription = "Imagem ${it + 1}",
                        tint = Color.Gray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Ingredientes
        Text(
            text = "Ingredientes:",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black
        )
        Text(
            text = """
                - 1 xícara de arroz lavado
                - 1 dente de alho amassado
                - 2 xícaras de água fervente
                - 1/4 de cebola picada
                - Azeite e sal a gosto
            """.trimIndent(),
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Materiais
        Text(
            text = "Materiais:",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black
        )
        Text(
            text = """
                - Panela
                - Colher de pau
                - Copo medidor
            """.trimIndent(),
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Modo de preparo
        Text(
            text = "Modo de preparo:",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black
        )
        Text(
            text = """
                1. Refogue o alho e a cebola no azeite.
                2. Coloque o arroz e deixe fritar por cerca de 30 segundos.
                3. Adicione a água fervente e o sal.
                4. Abaixe o fogo e deixe cozinhar até a água quase secar.
                5. Tampe a panela e aguarde cerca de 20 minutos antes de servir.
                6. Se desejar fazer mais, é só seguir as proporções.
            """.trimIndent(),
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}
