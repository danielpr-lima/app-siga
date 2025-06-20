// File: app/src/main/java/br.com.alura.orgs.fragments/CalendarioFragment.kt

package br.com.alura.orgs.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.alura.orgs.R

class CalendarioFragment : Fragment() {

    private lateinit var openCalendarButton: Button

    // ** IMPORTANTE: SUBSTITUA ESTA URL PELO LINK REAL DO SEU PDF **
    // Você pode usar um link para o seu PDF no Google Drive, OneDrive, site da instituição, etc.
    private val CALENDAR_PDF_URL = "https://www.fatecmaua.com.br/wp-content/uploads/2025/02/Calendario-Escolar-2025_1-aprovado.pdf" // <-- MUDAR AQUI!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calendario, container, false)
        openCalendarButton = view.findViewById(R.id.btn_abrir_calendario)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        openCalendarButton.setOnClickListener {
            openCalendarPdf()
        }
    }

    private fun openCalendarPdf() {
        try {
            val intent = Intent(Intent.ACTION_VIEW) // Ação para visualizar algo
            intent.data = Uri.parse(CALENDAR_PDF_URL) // O dado a ser visualizado é a URL
            startActivity(intent) // Inicia a Activity que pode lidar com essa URL (ex: navegador)
        } catch (e: Exception) {
            Toast.makeText(context, "Erro ao abrir o calendário: ${e.message}", Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }
}