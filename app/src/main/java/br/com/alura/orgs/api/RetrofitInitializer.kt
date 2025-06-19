package br.com.alura.orgs.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    private val BASE_URL = "https://c950-2804-14d-78c3-8803-a468-1246-439f-2f29.ngrok-free.app/api/" // <-- Mantenha sua URL atualizada

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun authService(): AuthService = retrofit.create(AuthService::class.java)

    fun studentService(): StudentService = retrofit.create(StudentService::class.java)
}