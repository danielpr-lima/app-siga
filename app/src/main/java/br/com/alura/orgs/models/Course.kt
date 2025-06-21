package br.com.alura.orgs.models

import com.google.gson.annotations.SerializedName

data class Course(
    @SerializedName("_id") val id: String,
    val nome: String,
    val codigo: String,
    val descricao: String,
    val duracao: Int,
    val coordenador: Coordenador?,
    val materias: List<String>?,
    val createdAt: String?,
    val updatedAt: String?
)

data class Coordenador(
    val nome: String?,
    val email: String?
)