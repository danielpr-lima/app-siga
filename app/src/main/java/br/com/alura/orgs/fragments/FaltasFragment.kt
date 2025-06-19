// File: app/src/main/java/br.com.alura.orgs.fragments/FaltasFragment.kt

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
import br.com.alura.orgs.adapter.FaltasAdapter // Importe o FaltasAdapter
import br.com.alura.orgs.api.RetrofitInitializer
import kotlinx.coroutines.launch

class FaltasFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private val studentService by lazy { RetrofitInitializer().studentService() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_faltas, container, false)
        recyclerView = view.findViewById(R.id.faltas_recyclerview)
        progressBar = view.findViewById(R.id.faltas_progress_bar)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadStudentFaltas()
    }

    private fun loadStudentFaltas() {
        progressBar.visibility = View.VISIBLE

        val sharedPreferences = requireActivity().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("auth_token", null)

        if (token == null) {
            Toast.makeText(context, "Erro: Token de autenticação não encontrado.", Toast.LENGTH_LONG).show()
            progressBar.visibility = View.GONE
            return
        }

        lifecycleScope.launch {
            try {
                val response = studentService.getAuthenticatedStudentData("Bearer $token")

                if (response.isSuccessful) {
                    val student = response.body()
                    student?.let { user ->
                        if (!user.materias.isNullOrEmpty()) {
                            recyclerView.layoutManager = LinearLayoutManager(context)
                            recyclerView.adapter = FaltasAdapter(user.materias) // Usa o FaltasAdapter
                        } else {
                            Toast.makeText(context, "Nenhuma matéria ou falta encontrada.", Toast.LENGTH_LONG).show()
                        }
                    } ?: run {
                        Toast.makeText(context, "Dados do aluno vazios.", Toast.LENGTH_LONG).show()
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    Toast.makeText(context, "Erro ao carregar faltas: ${errorBody ?: "Erro desconhecido"}", Toast.LENGTH_LONG).show()
                    Log.e("FaltasFragment", "Erro ao carregar faltas: ${response.code()} - $errorBody")
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Erro de conexão: ${e.message}", Toast.LENGTH_LONG).show()
                Log.e("FaltasFragment", "Erro de conexão ao carregar faltas:", e)
            } finally {
                progressBar.visibility = View.GONE
            }
        }
    }
}