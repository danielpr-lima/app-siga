package br.com.alura.orgs.models

import com.google.gson.annotations.SerializedName

data class Subject(
    @SerializedName("_id") val id: String, // Mapeia o _id do MongoDB
    val nome: String,
    val codigo: String,
    val descricao: String,
    val cargaHoraria: Int,
    val professor: Professor,
    val semestre: Int,
    // A lista de pré-requisitos geralmente é uma lista de IDs de matérias.
    // val preRequisitos: List<String>,
    val createdAt: String,
    val updatedAt: String
)

data class Professor(
    val nome: String,
    val email: String
)