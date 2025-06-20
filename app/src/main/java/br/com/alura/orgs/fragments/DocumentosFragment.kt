// File: app/src/main/java/br.com.alura.orgs.fragments/DocumentosFragment.kt

package br.com.alura.orgs.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import br.com.alura.orgs.R

class DocumentosFragment : Fragment() {

    private lateinit var statusTextView: TextView
    private lateinit var dataTextView: TextView
    private lateinit var solicitarButton: Button

    // Constantes para SharedPreferences
    private val PREFS_NAME = "DocumentoPrefs"
    private val KEY_STATUS = "comprovante_status"
    private val KEY_DATA = "comprovante_data"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_documentos, container, false)
        statusTextView = view.findViewById(R.id.documento_status_valor)
        dataTextView = view.findViewById(R.id.documento_data_valor)
        solicitarButton = view.findViewById(R.id.btn_solicitar_comprovante)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadDocumentStatus() // Carrega o status ao criar a view

        solicitarButton.setOnClickListener {
            solicitarComprovante()
        }
    }

    private fun loadDocumentStatus() {
        val prefs = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val status = prefs.getString(KEY_STATUS, "Nenhum pedido")
        val data = prefs.getString(KEY_DATA, "--")

        statusTextView.text = status
        dataTextView.text = data

        // Atualiza o estado do botão baseado no status (ex: desabilita se já estiver Pendente)
        solicitarButton.isEnabled = status != "Pendente"
        if (status == "Pendente") {
            solicitarButton.text = "Solicitação Enviada (Aguardando)"
        } else if (status == "Concluído") {
            solicitarButton.text = "Solicitar Novamente"
        } else {
            solicitarButton.text = "Solicitar Comprovante de Matrícula"
        }
    }

    private fun solicitarComprovante() {
        // Simulação da solicitação
        val newStatus = "Pendente"
        val currentTime = SimpleDateFormat("dd/MM/yyyy - HH:mm", Locale.getDefault()).format(Date())

        // Salvar status e data em SharedPreferences
        val prefs = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        with(prefs.edit()) {
            putString(KEY_STATUS, newStatus)
            putString(KEY_DATA, currentTime)
            apply()
        }

        // Atualizar UI
        loadDocumentStatus()
        Toast.makeText(context, "Solicitação de comprovante enviada!", Toast.LENGTH_SHORT).show()

        // TODO: (Opcional) Simular um "concluído" após algum tempo para demonstração
        // Handler().postDelayed({
        //     with(prefs.edit()) {
        //         putString(KEY_STATUS, "Concluído")
        //         apply()
        //     }
        //     loadDocumentStatus()
        //     Toast.makeText(context, "Seu comprovante está pronto para download!", Toast.LENGTH_LONG).show()
        // }, 5000) // 5 segundos
    }
}