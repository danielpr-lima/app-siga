package br.com.alura.orgs.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.R
import br.com.alura.orgs.adapter.NotasAdapter
import br.com.alura.orgs.api.RetrofitInitializer
import kotlinx.coroutines.launch

class NotasFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private val studentService by lazy { RetrofitInitializer().studentService() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notas, container, false)
        recyclerView = view.findViewById(R.id.notas_recyclerview)
        progressBar = view.findViewById(R.id.notas_progress_bar)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadStudentNotas()
    }

    private fun loadStudentNotas() {
        progressBar.visibility = View.VISIBLE // Mostra o ProgressBar

        // Obtém o token salvo nas SharedPreferences
        val sharedPreferences = requireActivity().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("auth_token", null)

        if (token == null) {
            Toast.makeText(context, "Erro: Token de autenticação não encontrado.", Toast.LENGTH_LONG).show()
            progressBar.visibility = View.GONE // Esconde o ProgressBar
            return
        }

        lifecycleScope.launch {
            try {
                // Requisição autenticada
                val response = studentService.getAuthenticatedStudentData("Bearer $token")

                if (response.isSuccessful) {
                    val student = response.body()
                    student?.let { user ->
                        // Verifica se o aluno tem matérias matriculadas
                        if (!user.materias.isNullOrEmpty()) {
                            // Configura o RecyclerView
                            recyclerView.layoutManager = LinearLayoutManager(context)
                            recyclerView.adapter = NotasAdapter(user.materias)
                        } else {
                            Toast.makeText(context, "Nenhuma matéria ou nota encontrada.", Toast.LENGTH_LONG).show()
                        }
                    } ?: run {
                        Toast.makeText(context, "Dados do aluno vazios.", Toast.LENGTH_LONG).show()
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    Toast.makeText(context, "Erro ao carregar notas: ${errorBody ?: "Erro desconhecido"}", Toast.LENGTH_LONG).show()
                    Log.e("NotasFragment", "Erro ao carregar notas: ${response.code()} - $errorBody")
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Erro de conexão: ${e.message}", Toast.LENGTH_LONG).show()
                Log.e("NotasFragment", "Erro de conexão ao carregar notas:", e)
            } finally {
                progressBar.visibility = View.GONE // Esconde o ProgressBar
            }
        }
    }
}