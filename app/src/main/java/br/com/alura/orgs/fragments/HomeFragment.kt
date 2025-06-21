package br.com.alura.orgs.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.R
import br.com.alura.orgs.adapter.NoticiasAdapter // Importe
import br.com.alura.orgs.models.Noticia // Importe
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class HomeFragment : Fragment() {

    private lateinit var noticiasRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        noticiasRecyclerView = view.findViewById(R.id.home_noticias_recyclerview) // ID do RecyclerView no home_fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNoticiasRecyclerView() // Configura o RecyclerView de notícias
    }

    private fun setupNoticiasRecyclerView() {
        noticiasRecyclerView.layoutManager = LinearLayoutManager(context)
        noticiasRecyclerView.adapter = NoticiasAdapter(getStaticNoticias())
        noticiasRecyclerView.isNestedScrollingEnabled = false
    }

    private fun getStaticNoticias(): List<Noticia> {
        val currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        return listOf(
            Noticia(
                "Novas Regras para Matrícula 2025",
                currentDate,
                "Prezados alunos, informamos que as novas regras para matrícula para o ano letivo de 2025 foram publicadas no portal acadêmico. Fiquem atentos aos prazos e documentos necessários. Acesse o portal para mais informações."
            ),
            Noticia(
                "Evento: Semana da Tecnologia",
                "15/07/2025",
                "Participe da nossa Semana da Tecnologia, com palestras e workshops sobre as últimas tendências em desenvolvimento de software e inteligência artificial. Inscrições abertas!"
            ),
            Noticia(
                "Aviso de Recesso Acadêmico",
                "01/07/2025",
                "Informamos que haverá recesso acadêmico do dia 01/07/2025 ao dia 05/07/2025. As atividades retornarão normalmente no dia 08/07/2025. Bom descanso a todos!"
            )
        )
    }
}