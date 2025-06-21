//package br.com.alura.orgs.fragments
//
//import android.content.Context
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ProgressBar
//import android.widget.TextView
//import android.widget.Toast
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.lifecycleScope
//import br.com.alura.orgs.R
//import br.com.alura.orgs.api.RetrofitInitializer
//import kotlinx.coroutines.launch
//
//class HomeFragment : Fragment() {
//
//    private lateinit var nomeAlunoTextView: TextView
//    private lateinit var cpfTextView: TextView
//    private lateinit var raTextView: TextView
//    private lateinit var emailTextView: TextView
//    private lateinit var cursoTextView: TextView
//    private lateinit var progressBar: ProgressBar
//
//    private val studentService by lazy { RetrofitInitializer().studentService() }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_home, container, false)
////        nomeAlunoTextView = view.findViewById(R.id.home_nome_aluno_valor)
////        cpfTextView = view.findViewById(R.id.home_cpf_valor)
////        raTextView = view.findViewById(R.id.home_ra_valor)
////        emailTextView = view.findViewById(R.id.home_email_valor)
////        cursoTextView = view.findViewById(R.id.home_curso_valor)
////        progressBar = view.findViewById(R.id.home_progress_bar)
//
//        return view
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        loadStudentData()
//    }
//
//    private fun loadStudentData() {
//        progressBar.visibility = View.VISIBLE // Mostra o ProgressBar
//
//        val sharedPreferences = requireActivity().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
//        val token = sharedPreferences.getString("auth_token", null)
//
//        if (token == null) {
//            Toast.makeText(context, "Erro: Token de autenticação não encontrado.", Toast.LENGTH_LONG).show()
//            progressBar.visibility = View.GONE
//            return
//        }
//
//        lifecycleScope.launch {
//            try {
//                // Requisição autenticada para obter dados completos do aluno
//                val response = studentService.getAuthenticatedStudentData("Bearer $token")
//
//                if (response.isSuccessful) {
//                    val student = response.body()
//                    student?.let { user ->
//                        // Preenche os TextViews com os dados do aluno
//                        nomeAlunoTextView.text = user.nome
//                        cpfTextView.text = user.cpf
//                        raTextView.text = user.ra ?: "N/A" // RA pode ser nulo
//                        emailTextView.text = user.email
//                        // O nome do curso vem do objeto 'curso' populado (se a API o popular)
////                        cursoTextView.text = user.curso?.nome ?: "Não informado" // 'curso' é um objeto Course populado
//                    } ?: run {
//                        Toast.makeText(context, "Dados do aluno vazios.", Toast.LENGTH_LONG).show()
//                    }
//                } else {
//                    val errorBody = response.errorBody()?.string()
//                    Toast.makeText(context, "Erro ao carregar dados do aluno: ${errorBody ?: "Erro desconhecido"}", Toast.LENGTH_LONG).show()
//                    Log.e("HomeFragment", "Erro ao carregar dados do aluno: ${response.code()} - $errorBody")
//                }
//            } catch (e: Exception) {
//                Toast.makeText(context, "Erro de conexão: ${e.message}", Toast.LENGTH_LONG).show()
//                Log.e("HomeFragment", "Erro de conexão ao carregar dados do aluno:", e)
//            } finally {
//                progressBar.visibility = View.GONE // Esconde o ProgressBar
//            }
//        }
//    }
//}