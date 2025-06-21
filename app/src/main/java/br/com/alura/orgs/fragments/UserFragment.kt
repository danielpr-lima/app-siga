// File: app/src/main/java/br.com.alura.orgs.fragments/UserFragment.kt

package br.com.alura.orgs.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import br.com.alura.orgs.R
import br.com.alura.orgs.api.RetrofitInitializer
import kotlinx.coroutines.launch

class UserFragment : Fragment() {

    private lateinit var nomeAlunoTextView: TextView
    private lateinit var raTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var cursoTextView: TextView
    private lateinit var progressBar: ProgressBar

    private val studentService by lazy { RetrofitInitializer().studentService() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user, container, false)
        nomeAlunoTextView = view.findViewById(R.id.user_nome_aluno_valor)
        raTextView = view.findViewById(R.id.user_ra_valor)
        emailTextView = view.findViewById(R.id.user_email_valor)
        cursoTextView = view.findViewById(R.id.user_curso_valor)
        progressBar = view.findViewById(R.id.user_progress_bar)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadUserData()
    }

    private fun loadUserData() {
        progressBar.visibility = View.VISIBLE // Mostra o ProgressBar

        val sharedPreferences = requireActivity().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("auth_token", null)

        if (token == null) {
            Toast.makeText(context, "Erro: Token de autenticação não encontrado.", Toast.LENGTH_LONG).show()
            progressBar.visibility = View.GONE
            return
        }

        lifecycleScope.launch {
            try {
                // Requisição autenticada para obter dados completos do aluno
                val response = studentService.getAuthenticatedStudentData("Bearer $token")

                if (response.isSuccessful) {
                    val user = response.body()
                    user?.let { aluno ->
                        // Preenche os TextViews com os dados do aluno
                        nomeAlunoTextView.text = aluno.nome
                        raTextView.text = aluno.ra ?: "N/A" // RA pode ser nulo
                        emailTextView.text = aluno.email
                        // O nome do curso vem do objeto 'curso' populado
                        cursoTextView.text = aluno.curso?.nome ?: "Não informado" // 'curso' é um objeto Course populado
                    } ?: run {
                        Toast.makeText(context, "Dados do usuário vazios.", Toast.LENGTH_LONG).show()
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    Toast.makeText(context, "Erro ao carregar dados do usuário: ${errorBody ?: "Erro desconhecido"}", Toast.LENGTH_LONG).show()
                    Log.e("UserFragment", "Erro ao carregar dados do usuário: ${response.code()} - $errorBody")
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Erro de conexão: ${e.message}", Toast.LENGTH_LONG).show()
                Log.e("UserFragment", "Erro de conexão ao carregar dados do usuário:", e)
            } finally {
                progressBar.visibility = View.GONE // Esconde o ProgressBar
            }
        }
    }
}