package br.com.alura.orgs.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.R
import br.com.alura.orgs.model.Materia

class MateriaAdapter(
    private val materias: List<Materia>
) : RecyclerView.Adapter<MateriaAdapter.MateriaViewHolder>() {

    inner class MateriaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNome = itemView.findViewById<TextView>(R.id.tvMateriaNome)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MateriaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_materia, parent, false)
        return MateriaViewHolder(view)
    }

    override fun getItemCount() = materias.size

    override fun onBindViewHolder(holder: MateriaViewHolder, position: Int) {
        val materia = materias[position]
        holder.tvNome.text = materia.materia
    }
}
