//package br.com.alura.orgs.adapter
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import br.com.alura.orgs.R
//import br.com.alura.orgs.models.Materia
//
//class MateriaAdapter(
//    private val materias: List<Materia>
//) : RecyclerView.Adapter<MateriaAdapter.MateriaViewHolder>() {
//
//    inner class MateriaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val tvNome = itemView.findViewById<TextView>(R.id.tvMateriaNome)
//        val tvP1 = itemView.findViewById<TextView>(R.id.tvP1)
//        val tvP2 = itemView.findViewById<TextView>(R.id.tvP2)
//        val tvP3 = itemView.findViewById<TextView>(R.id.tvP3)
//        val tvAtividade = itemView.findViewById<TextView>(R.id.tvAtividade)
//        val tvMediaFinal = itemView.findViewById<TextView>(R.id.tvMediaFinal)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MateriaViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_materia, parent, false)
//        return MateriaViewHolder(view)
//    }
//
//    override fun getItemCount() = materias.size
//
//    override fun onBindViewHolder(holder: MateriaViewHolder, position: Int) {
//        val materia = materias[position]
//
//        holder.tvNome.text = materia.materia
//
//        val notas = materia.notas
//        // Exibir as notas
//        holder.tvP1.text = "P1: ${notas.P1}"
//        holder.tvP2.text = "P2: ${notas.P2}"
//        holder.tvP3.text = "P3: ${notas.P3}"
//        holder.tvAtividade.text = "Atividade: ${notas.T}"
//
//        // Calcular média final mudar calculo
//        val mediaFinal = (notas.P1 + notas.P2 + notas.P3 + notas.T) / 4
//        holder.tvMediaFinal.text = "Média final: %.2f".format(mediaFinal)
//    }
//}
