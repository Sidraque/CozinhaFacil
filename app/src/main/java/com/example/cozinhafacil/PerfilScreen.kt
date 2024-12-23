import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

@Composable
fun PerfilScreen(navController: NavHostController) {
    var nomeUsuario by remember { mutableStateOf("Carregando...") }
    var emailUsuario by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            emailUsuario = user.email ?: "Email não disponível"

            val databaseReference = FirebaseDatabase.getInstance().getReference("users/${user.uid}")
            databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    nomeUsuario = snapshot.child("nome").getValue(String::class.java) ?: "Usuário"
                    isLoading = false
                }

                override fun onCancelled(error: DatabaseError) {
                    nomeUsuario = "Erro ao carregar"
                    isLoading = false
                }
            })
        } else {
            nomeUsuario = "Usuário não logado"
            isLoading = false
        }
    }

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

        Text(
            text = if (isLoading) "Carregando..." else nomeUsuario,
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

        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(horizontal = 32.dp)
        ) {
            MenuItem(icon = Icons.Filled.List, label = "Minhas Receitas", onClick = { navController.navigate("minhasReceitas") })
            MenuItem(icon = Icons.Filled.Star, label = "Favoritos", onClick = { navController.navigate("favoritos") })
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .padding(32.dp)
                .height(56.dp)
                .fillMaxWidth()
                .background(Color(0xFFFFEB3B), RoundedCornerShape(16.dp))
                .clickable {
                    FirebaseAuth.getInstance().signOut()
                    navController.navigate("login") // Redireciona para a tela de login após o logout
                },
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
fun MenuItem(icon: ImageVector, label: String, onClick: () -> Unit)  {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable(onClick = onClick),
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
