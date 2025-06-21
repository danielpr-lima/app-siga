package br.com.alura.orgs.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.R
import br.com.alura.orgs.models.MateriaMatriculada
import br.com.alura.orgs.models.Subject

class FaltasAdapter(private val materias: List<MateriaMatriculada>) :
    RecyclerView.Adapter<FaltasAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nomeMateria: TextView = view.findViewById(R.id.item_materia_nome_falta)
        val nomeProfessor: TextView = view.findViewById(R.id.item_materia_professor_falta)
        val faltasDetalhe: TextView = view.findViewById(R.id.item_materia_faltas_detalhe)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_materia_falta, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val materiaMatriculada = materias[position]

        holder.nomeMateria.text = materiaMatriculada.materia?.nome ?: "Matéria Desconhecida"
        holder.nomeProfessor.text = "Professor: ${materiaMatriculada.materia?.professor?.nome ?: "Não informado"}"

        val totalFaltas = materiaMatriculada.faltas
        val aulasTotais = materiaMatriculada.presenca.aulasTotais
        val percentualFaltas = if (aulasTotais > 0) (totalFaltas.toDouble() / aulasTotais) * 100 else 0.0

        val faltasText = "Faltas: $totalFaltas de $aulasTotais aulas (${String.format("%.1f", percentualFaltas)}%)"
        holder.faltasDetalhe.text = faltasText

        // Opcional: Mudar a cor se o percentual de faltas for alto (ex: > 25%)
        if (percentualFaltas > 25.0) { // Se o limite de faltas for 25%
            holder.faltasDetalhe.setTextColor(Color.RED)
        } else {
            holder.faltasDetalhe.setTextColor(Color.BLACK)
        }
    }

    override fun getItemCount(): Int {
        return materias.size
    }
}