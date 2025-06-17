package br.com.alura.orgs.models

import com.google.gson.annotations.SerializedName

data class Course(
    @SerializedName("_id") val id: String, // Mapeia o _id do MongoDB
    val nome: String,
    val codigo: String,
    val descricao: String,
    val duracao: Int, // Em semestres
    val coordenador: Coordenador,
    // A lista de IDs de matérias geralmente não é necessária para o objeto Course em si no app,
    // a menos que você precise desses IDs para fazer outras requisições.
    // val materias: List<String>,
    val createdAt: String,
    val updatedAt: String
)

data class Coordenador(
    val nome: String?, // Pode ser nulo
    val email: String? // Pode ser nulo
)