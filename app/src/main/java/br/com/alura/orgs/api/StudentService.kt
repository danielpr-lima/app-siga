package br.com.alura.orgs.api

import br.com.alura.orgs.models.User // Importe a data class do usuário COMPLETO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header // Para enviar o token de autenticação

public interface StudentService { // Torne a interface pública

    @GET("student") // Endpoint para buscar dados do aluno autenticado
    suspend fun getAuthenticatedStudentData(
        @Header("Authorization") authToken: String // Envia o token JWT no cabeçalho
    ): Response<User> // Esperamos receber um objeto User completo
}