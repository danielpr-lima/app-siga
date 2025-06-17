package br.com.alura.orgs.api

import br.com.alura.orgs.models.LoginRequest
import br.com.alura.orgs.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

public interface AuthService {

    @POST("auth/login") // Verifique se o caminho relativo está correto
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}