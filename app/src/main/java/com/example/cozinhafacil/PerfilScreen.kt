import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController

@Composable
fun PerfilScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Botão Voltar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Voltar",
                tint = Color.Black,
                modifier = Modifier
                    .size(32.dp)
                    .clickable { navController.popBackStack() }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Ícone do perfil
        Box(
            modifier = Modifier
                .size(140.dp)
                .background(Color(0xFFFFF9C4), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Face,
                contentDescription = "Perfil",
                tint = Color(0xFFFFEB3B),
                modifier = Modifier.size(210.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Nome do usuário
        Text(
            text = "Username",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botão Editar Perfil
        Box(
            modifier = Modifier
                .padding(horizontal = 50.dp)
                .height(50.dp)
                .fillMaxWidth()
                .background(Color(0xFFFFEB3B), RoundedCornerShape(20.dp))
                .clickable { navController.navigate("editarPerfil") },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Editar Perfil",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Opções de menu
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(horizontal = 32.dp)
        ) {
            MenuItem(icon = Icons.Filled.List, label = "Minhas Receitas")
            MenuItem(icon = Icons.Filled.Star, label = "Favoritos")
            MenuItem(icon = Icons.Filled.Lock, label = "Alterar Senha")
        }

        Spacer(modifier = Modifier.weight(1f))

        // Botão Sair da Conta
        Box(
            modifier = Modifier
                .padding(32.dp)
                .height(56.dp)
                .fillMaxWidth()
                .background(Color(0xFFFFEB3B), RoundedCornerShape(16.dp))
                .clickable { /* Ação para sair da conta */ },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Sair da Conta",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}

@Composable
fun MenuItem(icon: ImageVector, label: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable { /* Ação do menu */ },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Color(0xFFFFEB3B),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}
