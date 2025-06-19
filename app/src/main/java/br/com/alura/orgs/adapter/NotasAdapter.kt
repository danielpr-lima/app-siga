package br.com.alura.orgs.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.R
import br.com.alura.orgs.models.MateriaMatriculada
import br.com.alura.orgs.models.Notas
import br.com.alura.orgs.models.Subject

class NotasAdapter(private val materias: List<MateriaMatriculada>) :
    RecyclerView.Adapter<NotasAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nomeMateria: TextView = view.findViewById(R.id.item_materia_nome)
        val nomeProfessor: TextView = view.findViewById(R.id.item_materia_professor)
        val notaP1: TextView = view.findViewById(R.id.item_materia_nota_p1)
        val notaP2: TextView = view.findViewById(R.id.item_materia_nota_p2)
        val notaT: TextView = view.findViewById(R.id.item_materia_nota_t)
        val notaP3: TextView = view.findViewById(R.id.item_materia_nota_p3)
        val media: TextView = view.findViewById(R.id.item_materia_media)
        // REMOVIDO: val faltas: TextView = view.findViewById(R.id.item_materia_faltas)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_materia_nota, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val materiaMatriculada = materias[position]

        holder.nomeMateria.text = materiaMatriculada.materia?.nome ?: "Matéria Desconhecida"
        holder.nomeProfessor.text = "Professor: ${materiaMatriculada.materia?.professor?.nome ?: "Não informado"}"

        // Notas (verificando se não são nulas)
        holder.notaP1.text = "P1: ${materiaMatriculada.notas?.P1?.toString() ?: "-"}"
        holder.notaP2.text = "P2: ${materiaMatriculada.notas?.P2?.toString() ?: "-"}"
        holder.notaT.text = "T: ${materiaMatriculada.notas?.T?.toString() ?: "-"}"
        holder.notaP3.text = "P3: ${materiaMatriculada.notas?.P3?.toString() ?: "-"}"

        // Cálculo da Média
        val mediaCalculada = calcularMedia(materiaMatriculada.notas)
        holder.media.text = "Média: ${String.format("%.1f", mediaCalculada)}"

        // REMOVIDO: Lógica para exibir faltas
        // holder.faltas.text = "Faltas: ${materiaMatriculada.faltas} de ${materiaMatriculada.presenca.aulasTotais} aulas"
    }

    override fun getItemCount(): Int {
        return materias.size
    }

    private fun calcularMedia(notas: Notas?): Double {
        if (notas == null) return 0.0

        val listaNotasValidas = mutableListOf<Double>()
        notas.P1?.let { listaNotasValidas.add(it) }
        notas.P2?.let { listaNotasValidas.add(it) }
        notas.T?.let { listaNotasValidas.add(it) }
        notas.P3?.let { listaNotasValidas.add(it) }

        return if (listaNotasValidas.isNotEmpty()) {
            listaNotasValidas.average()
        } else {
            0.0
        }
    }
}