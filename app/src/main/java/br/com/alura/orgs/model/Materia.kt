package br.com.alura.orgs.model

data class Aluno(
    val role: String,
    val cpf: String,
    val nome: String,
    val email: String,
    val curso: String,
    val materias: List<Materia>
)

data class Materia(
    val materia: String,
    val notas: Notas,
    val presenca: Presenca
)

data class Notas(
    val P1: Double,
    val P2: Double,
    val T: Double,
    val P3: Double
)

data class Presenca(
    val aulasTotais: Int,
    val faltas: Int
)

