package br.com.alura.orgs.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("_id") val id: String,
    val role: String,
    val cpf: String,
    val ra: String?,
    val nome: String,
    val email: String,
    @SerializedName("curso") val courseId: String?,
    val materias: List<MateriaMatriculada>?,
    val fotoPerfil: String?,
    val createdAt: String,
    val updatedAt: String
)

data class MateriaMatriculada(
    @SerializedName("_id") val id: String?, // ID do subdocumento de matrícula
    @SerializedName("materia") val materia: Subject?, // <<<<< MUDANÇA CRÍTICA: Agora é do tipo Subject?
    val notas: Notas?,
    val faltas: Int,
    val presenca: Presenca
    // Campos 'nome' e 'professor' REMOVIDOS daqui, pois eles estão dentro do objeto 'materia' (Subject)
)

data class Notas(
    @SerializedName("_id") val id: String?,
    val P1: Double?,
    val P2: Double?,
    val T: Double?,
    val P3: Double?
)

data class Presenca(
    @SerializedName("_id") val id: String?,
    val aulasTotais: Int,
    val faltas: Int
)