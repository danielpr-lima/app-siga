package br.com.alura.orgs.api

import br.com.alura.orgs.models.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

public interface StudentService {

    @GET("student")
    suspend fun getAuthenticatedStudentData(
        @Header("Authorization") authToken: String
    ): Response<User>
}