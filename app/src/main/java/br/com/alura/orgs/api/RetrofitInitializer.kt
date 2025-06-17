package br.com.alura.orgs.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitInitializer {

    // ***** PONTO CRÍTICO: BASE_URL *****
    // - Para Emulador Android (AVD): "http://10.0.2.2:3000/api/"
    // - Para Dispositivo Físico (seu celular na mesma rede Wi-Fi): "http://SEU_IP_DA_MAQUINA:3000/api/"
    //   Para descobrir o IP da sua máquina: Abra o terminal/CMD e digite 'ipconfig' (Windows) ou 'ifconfig' / 'ip a' (Linux/macOS)
    private val BASE_URL = "http://10.0.2.2:3000/api/" // <--- VERIFIQUE E AJUSTE ESTA LINHA!

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun authService(): AuthService = retrofit.create(AuthService::class.java)

    // TODO: No futuro, adicione funções para outros serviços da API aqui
}