package br.com.alura.orgs.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    private val BASE_URL = "https://2aac-2804-14d-78c3-8803-d0a3-7faf-567c-2f92.ngrok-free.app/api/" // <-- Mantenha sua URL atualizada

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun authService(): AuthService = retrofit.create(AuthService::class.java)

    fun studentService(): StudentService = retrofit.create(StudentService::class.java)
}