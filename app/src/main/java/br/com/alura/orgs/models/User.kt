// File: app/src/main/java/br.com.alura.orgs/models/User.kt

package br.com.alura.orgs.models

import com.google.gson.annotations.SerializedName

data class User( // Esta será a classe principal para o modelo de usuário completo
    @SerializedName("_id") val id: String, // Mapeia o _id do MongoDB
    val role: String, // "aluno", "professor", "admin"
    val cpf: String,
    val ra: String?, // Registro acadêmico, pode ser nulo
    val nome: String,
    val email: String,
    @SerializedName("curso") val courseId: String?, // ID do curso, mapeia 'curso' do JS
    val materias: List<MateriaMatriculada>?, // Lista de matérias matriculadas, pode ser nula
    val fotoPerfil: String?, // URL da foto de perfil, pode ser nulo
    val createdAt: String,
    val updatedAt: String
)

data class MateriaMatriculada(
    @SerializedName("_id") val id: String?, // ID do subdocumento, pode ser nulo
    @SerializedName("materia") val subjectId: String, // ID da matéria referenciada
    val notas: Notas?, // Pode ser nulo
    val faltas: Int,
    val presenca: Presenca
)

data class Notas(
    @SerializedName("_id") val id: String?, // ID do subdocumento de notas, pode ser nulo
    val P1: Double?,
    val P2: Double?,
    val T: Double?,
    val P3: Double?
)

data class Presenca(
    @SerializedName("_id") val id: String?, // ID do subdocumento de presença, pode ser nulo
    val aulasTotais: Int,
    val faltas: Int
)