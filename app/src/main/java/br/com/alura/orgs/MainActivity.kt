// File: app/src/main/java/br.com.alura.orgs/MainActivity.kt

package br.com.alura.orgs

import android.graphics.PorterDuff // Importe
import android.graphics.PorterDuffColorFilter // Importe
import android.os.Bundle
import android.view.View
import android.widget.ImageView // Importe
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat // Importe
import androidx.fragment.app.Fragment
import br.com.alura.orgs.fragments.CalendarioFragment
import br.com.alura.orgs.fragments.DocumentosFragment
import br.com.alura.orgs.fragments.FaltasFragment
import br.com.alura.orgs.fragments.HomeFragment
import br.com.alura.orgs.fragments.UserFragment
import br.com.alura.orgs.fragments.NotasFragment

class MainActivity : AppCompatActivity() {


    private val menuItems = mapOf(
        R.id.btnUsuario to R.drawable.ic_usuario,
        R.id.btnHome to R.drawable.ic_home,
        R.id.btnNotas to R.drawable.ic_notas,
        R.id.btnFaltas to R.drawable.ic_faltas,
        R.id.btnDocumentos to R.drawable.ic_documento,
        R.id.btnCalendario to R.drawable.ic_calendario

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Fragmento padrão inicial e seleção do botão correspondente
        abrirFragment(HomeFragment(), R.id.btnHome)

        findViewById<ImageView>(R.id.btnUsuario).setOnClickListener {
            abrirFragment(UserFragment(), R.id.btnUsuario)
        }
        findViewById<ImageView>(R.id.btnHome).setOnClickListener {
            abrirFragment(HomeFragment(), R.id.btnHome)
        }
        findViewById<ImageView>(R.id.btnNotas).setOnClickListener {
            abrirFragment(NotasFragment(), R.id.btnNotas)
        }
        findViewById<ImageView>(R.id.btnFaltas).setOnClickListener {
            abrirFragment(FaltasFragment(), R.id.btnFaltas)
        }
        findViewById<ImageView>(R.id.btnDocumentos).setOnClickListener {
            abrirFragment(DocumentosFragment(), R.id.btnDocumentos)
        }
        findViewById<ImageView>(R.id.btnCalendario).setOnClickListener {
            abrirFragment(CalendarioFragment(), R.id.btnCalendario)
        }

    }

    private fun abrirFragment(fragment: Fragment, selectedButtonId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()

        updateMenuSelection(selectedButtonId)
    }

    private fun updateMenuSelection(selectedButtonId: Int) {
        val selectedColor = ContextCompat.getColor(this, R.color.menu_icon_selected) // Cor roxa
        val defaultColor = ContextCompat.getColor(this, R.color.menu_icon_default) // Cor padrão

        for ((buttonId, defaultDrawableResId) in menuItems) {
            val imageView = findViewById<ImageView>(buttonId)
            if (buttonId == selectedButtonId) {
                // Estado selecionado: fundo vermelho e ícone roxo
                imageView.isSelected = true // Ativa o estado 'selected' para o background drawable
                imageView.colorFilter = PorterDuffColorFilter(selectedColor, PorterDuff.Mode.SRC_IN) // Pinta o ícone
            } else {
                // Estado padrão: fundo transparente e ícone cor padrão
                imageView.isSelected = false // Desativa o estado 'selected'
                imageView.colorFilter = PorterDuffColorFilter(defaultColor, PorterDuff.Mode.SRC_IN) // Pinta o ícone de volta
            }
        }
    }
}