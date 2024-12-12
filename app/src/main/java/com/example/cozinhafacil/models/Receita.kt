package com.example.cozinhafacil.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

// Dentro da classe Receita
data class Receita(
    val titulo: String = "",
    val descricao: String = "",
    val autor: String = "",
    val ingredientes: String = "",
    val imagemUri: String? = null,
    var favoritado: Boolean = false
) {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "titulo" to titulo,
            "descricao" to descricao,
            "autor" to autor,
            "ingredientes" to ingredientes,
            "imagemUri" to imagemUri,
            "favoritado" to favoritado
        )
    }
}
