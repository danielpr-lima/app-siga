package br.com.alura.orgs.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.R
import br.com.alura.orgs.adapter.MateriaAdapter
import br.com.alura.orgs.model.Aluno
import br.com.alura.orgs.model.Materia
import br.com.alura.orgs.model.Notas
import br.com.alura.orgs.model.Presenca

class NotasFragment : Fragment() {

    private lateinit var adapter: MateriaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvMaterias)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Dados mock simulando retorno da API
        val alunoMock = Aluno(
            role = "aluno",
            cpf = "123.456.789-00",
            nome = "João da Silva",
            email = "joao@email.com",
            curso = "Engenharia de Software",
            materias = listOf(
                Materia(
                    materia = "Banco de Dados",
                    notas = Notas(P1 = 8.5, P2 = 7.0, T = 9.0, P3 = 6.5),
                    presenca = Presenca(aulasTotais = 40, faltas = 3)
                ),
                Materia(
                    materia = "Programação",
                    notas = Notas(P1 = 7.0, P2 = 8.0, T = 7.5, P3 = 8.0),
                    presenca = Presenca(aulasTotais = 40, faltas = 1)
                ),
                Materia(
                    materia = "Engenharia de Software",
                    notas = Notas(P1 = 9.0, P2 = 8.5, T = 8.0, P3 = 9.0),
                    presenca = Presenca(aulasTotais = 40, faltas = 0)
                )
            )
        )

        adapter = MateriaAdapter(alunoMock.materias)
        recyclerView.adapter = adapter
    }
}
