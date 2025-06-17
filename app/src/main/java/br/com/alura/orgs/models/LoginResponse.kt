package br.com.alura.orgs.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    val token: String // Agora só esperamos o token diretamente

)

// Representação simplificada do usuário para a resposta do login
data class UserLogin(
    @SerializedName("_id") val id: String, // O MongoDB usa "_id"
    val cpf: String,
    val nome: String, // Campo é 'nome' no seu schema User.js, não 'name'
    val email: String,
    val role: String // Ex: "aluno", "professor", "admin"
)