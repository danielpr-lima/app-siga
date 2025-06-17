package br.com.alura.orgs

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.alura.orgs.models.LoginRequest
import br.com.alura.orgs.models.User // Importe a data class User (completa)
import br.com.alura.orgs.api.RetrofitInitializer
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private val authService by lazy { RetrofitInitializer().authService() }
    private val studentService by lazy { RetrofitInitializer().studentService() } // ** NOVO SERVIÇO **

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val editLogin = findViewById<EditText>(R.id.editLogin)
        val editSenha = findViewById<EditText>(R.id.editSenha)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val cpf = editLogin.text.toString().trim()
            val senha = editSenha.text.toString().trim()

            if (cpf.isNotEmpty() && senha.isNotEmpty()) {
                Toast.makeText(this, "Tentando conectar à API...", Toast.LENGTH_SHORT).show()

                lifecycleScope.launch {
                    try {
                        // 1. Primeira Requisição: LOGIN (obtém apenas o token)
                        val loginRequest = LoginRequest(cpf = cpf, senha = senha)
                        val loginResponse = authService.login(loginRequest)

                        if (loginResponse.isSuccessful) {
                            val token = loginResponse.body()?.token // Obter o token
                            if (token != null) {
                                saveAuthToken(token) // Salvar o token imediatamente

                                // 2. Segunda Requisição: BUSCAR DADOS DO ALUNO AUTENTICADO
                                val studentDataResponse = studentService.getAuthenticatedStudentData(
                                    authToken = "Bearer $token" // Envia o token no formato "Bearer SEU_TOKEN"
                                )

                                if (studentDataResponse.isSuccessful) {
                                    val user = studentDataResponse.body()
                                    user?.let {
                                        // Login e busca de dados bem-sucedidos
                                        Toast.makeText(this@LoginActivity, "Bem-vindo, ${it.nome}!", Toast.LENGTH_SHORT).show()

                                        // Salvar informações do usuário completo (nome, id, role)
                                        saveUserId(it.id)
                                        saveUserRole(it.role)
                                        saveUserName(it.nome) // Salvar o nome que o Toast precisa

                                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                        startActivity(intent)
                                        finish()
                                    } ?: run {
                                        Toast.makeText(this@LoginActivity, "Dados do aluno vazios após login.", Toast.LENGTH_LONG).show()
                                    }
                                } else {
                                    val errorBody = studentDataResponse.errorBody()?.string()
                                    Toast.makeText(this@LoginActivity, "Erro ao buscar dados do aluno: ${errorBody ?: "Erro desconhecido"}", Toast.LENGTH_LONG).show()
                                }

                            } else {
                                Toast.makeText(this@LoginActivity, "Token de login não recebido.", Toast.LENGTH_LONG).show()
                            }
                        } else {
                            val errorBody = loginResponse.errorBody()?.string()
                            Toast.makeText(this@LoginActivity, "Falha no login: ${errorBody ?: "Erro desconhecido"}", Toast.LENGTH_LONG).show()
                        }
                    } catch (e: Exception) {
                        Toast.makeText(this@LoginActivity, "Erro de conexão: ${e.message}", Toast.LENGTH_LONG).show()
                        e.printStackTrace()
                    }
                }
            } else {
                Toast.makeText(this, "Preencha o CPF e a senha.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Funções saveAuthToken, saveUserId, saveUserRole, saveUserName permanecem as mesmas
    private fun saveAuthToken(token: String) {
        val sharedPreferences = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("auth_token", token)
            apply()
        }
    }

    private fun saveUserId(userId: String) {
        val sharedPreferences = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("user_id", userId)
            apply()
        }
    }

    private fun saveUserRole(userRole: String) {
        val sharedPreferences = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("user_role", userRole)
            apply()
        }
    }

    private fun saveUserName(userName: String) {
        val sharedPreferences = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("user_name", userName)
            apply()
        }
    }
}