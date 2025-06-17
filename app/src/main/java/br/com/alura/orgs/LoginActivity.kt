// File: app/src/main/java/br.com.alura.orgs/LoginActivity.kt

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
import br.com.alura.orgs.api.RetrofitInitializer // Ajuste se o nome do pacote for diferente
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private val authService by lazy {
        RetrofitInitializer().authService()
    }

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
                // *** ADICIONE ESTE TOAST AQUI ***
                Toast.makeText(this, "Tentando conectar à API...", Toast.LENGTH_SHORT).show()

                lifecycleScope.launch {
                    try {
                        val request = LoginRequest(cpf = cpf, senha = senha)
                        val response = authService.login(request)

                        if (response.isSuccessful) {
                            val loginResponse = response.body()
                            loginResponse?.let {
                                Toast.makeText(this@LoginActivity, "Bem-vindo, ${it.user.nome}!", Toast.LENGTH_SHORT).show()
                                saveAuthToken(it.token)
                                saveUserId(it.user.id)
                                saveUserRole(it.user.role)
                                saveUserName(it.user.nome)

                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            } ?: run {
                                Toast.makeText(this@LoginActivity, "Resposta do login vazia. Tente novamente.", Toast.LENGTH_LONG).show()
                            }
                        } else {
                            val errorBody = response.errorBody()?.string()
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