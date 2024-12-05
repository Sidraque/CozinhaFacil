package com.example.cozinhafacil.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

data class AuthResponse(val isSuccessful: Boolean, val message: String?)


class AuthRepository {
    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance().reference
    private val firebaseAuth = FirebaseAuth.getInstance()

    suspend fun registerUser(
        email: String,
        password: String,
        nome: String,
        sobrenome: String
    ): Result<Unit> {
        return try {
            // Criação do usuário no Firebase Authentication
            val userCredential = auth.createUserWithEmailAndPassword(email, password).await()

            // Adiciona os dados ao Realtime Database
            val userId = userCredential.user?.uid ?: throw Exception("ID do usuário não encontrado")
            val userData = mapOf(
                "nome" to nome,
                "sobrenome" to sobrenome,
                "email" to email
            )
            database.child("users").child(userId).setValue(userData).await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    // Função para fazer login no Firebase
    suspend fun login(email: String, senha: String): AuthResponse {
        return try {
            val authResult = firebaseAuth.signInWithEmailAndPassword(email, senha).await()
            AuthResponse(isSuccessful = true, message = null)
        } catch (e: Exception) {
            AuthResponse(isSuccessful = false, message = e.message)
        }
    }

    // Função para verificar se o usuário está autenticado
    fun isUserLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }

    // Função para obter o usuário atual
    fun getCurrentUser() = firebaseAuth.currentUser
}
