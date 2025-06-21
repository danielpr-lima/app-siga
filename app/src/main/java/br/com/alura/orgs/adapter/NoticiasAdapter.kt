package br.com.alura.orgs.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.R
import br.com.alura.orgs.models.Noticia // Importe a data class Noticia

class NoticiasAdapter(private val noticias: List<Noticia>) :
    RecyclerView.Adapter<NoticiasAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titulo: TextView = view.findViewById(R.id.item_noticia_titulo)
        val dataPublicacao: TextView = view.findViewById(R.id.item_noticia_data)
        val conteudo: TextView = view.findViewById(R.id.item_noticia_conteudo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_noticia, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val noticia = noticias[position]
        holder.titulo.text = noticia.titulo
        holder.dataPublicacao.text = "Publicado em: ${noticia.dataPublicacao}"
        holder.conteudo.text = noticia.conteudo
    }

    override fun getItemCount(): Int {
        return noticias.size
    }
}