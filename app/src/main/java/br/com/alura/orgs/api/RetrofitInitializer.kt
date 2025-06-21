package br.com.alura.orgs.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    private val BASE_URL = "https://f36e-2804-14d-78c3-8803-64fa-b12e-efa4-9d2d.ngrok-free.app/api/" // <-- Mantenha a URL atualizada

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun authService(): AuthService = retrofit.create(AuthService::class.java)

    fun studentService(): StudentService = retrofit.create(StudentService::class.java)
}