package com.example.cozinhafacil.repository

import com.example.cozinhafacil.models.User
import com.example.cozinhafacil.models.Receita
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class AuthRepository {
    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance().reference

    suspend fun registerUser(user: User, password: String): Result<Unit> {
        return try {
            val userCredential = auth.createUserWithEmailAndPassword(user.email, password).await()

            val userId = userCredential.user?.uid ?: throw Exception("ID do usuário não encontrado")
            database.child("users").child(userId).setValue(user).await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login(email: String, password: String): Result<User> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()

            val userId = auth.currentUser?.uid ?: throw Exception("ID do usuário não encontrado")

            val snapshot = database.child("users").child(userId).get().await()
            val user = snapshot.getValue(User::class.java)
                ?: throw Exception("Dados do usuário não encontrados")

            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    fun getCurrentUser(): User? {
        val firebaseUser = auth.currentUser ?: return null
        return User(
            nome = firebaseUser.displayName ?: "",
            sobrenome = "",
            email = firebaseUser.email ?: ""
        )
    }

    suspend fun salvarReceita(receita: Map<String, Any?>): Result<Unit> {
        return try {
            database.child("receitas").push().setValue(receita).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun buscarReceitas(): Result<List<Receita>> {
        return try {
            val snapshot = database.child("receitas").get().await()
            val receitas = snapshot.children.mapNotNull { it.getValue(Receita::class.java) }
            Result.success(receitas)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
