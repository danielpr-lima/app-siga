package br.com.alura.orgs

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val editLogin = findViewById<EditText>(R.id.editLogin)
        val editSenha = findViewById<EditText>(R.id.editSenha)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val login = editLogin.text.toString().trim()
            val senha = editSenha.text.toString().trim()

            if (login.isNotEmpty() && senha.isNotEmpty()) {
                Toast.makeText(this, "Logado com sucesso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                finish()
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
