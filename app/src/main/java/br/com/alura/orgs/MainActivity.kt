package br.com.alura.orgs

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.alura.orgs.fragments.CalendarioFragment
import br.com.alura.orgs.fragments.DocumentosFragment
import br.com.alura.orgs.fragments.FaltasFragment
import br.com.alura.orgs.fragments.HomeFragment
import br.com.alura.orgs.fragments.MaterialFragment
import br.com.alura.orgs.fragments.NotasFragment
//import br.com.alura.orgs.fragments.NotasFragment
import br.com.alura.orgs.fragments.NoticiasFragment

class MainActivity : AppCompatActivity() {

    private fun abrirFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        abrirFragment(HomeFragment()) // Fragmento padrão inicial

        findViewById<View>(R.id.btnHome).setOnClickListener {
            abrirFragment(HomeFragment())
        }
        findViewById<View>(R.id.btnNotas).setOnClickListener {
            abrirFragment(NotasFragment())
        }
        findViewById<View>(R.id.btnFaltas).setOnClickListener {
            abrirFragment(FaltasFragment())
        }
        findViewById<View>(R.id.btnDocumentos).setOnClickListener {
            abrirFragment(DocumentosFragment())
        }
        findViewById<View>(R.id.btnCalendario).setOnClickListener {
            abrirFragment(CalendarioFragment())
        }
        findViewById<View>(R.id.btnMaterial).setOnClickListener {
            abrirFragment(MaterialFragment())
        }
        findViewById<View>(R.id.btnNoticias).setOnClickListener {
            abrirFragment(NoticiasFragment())
        }
    }
}
