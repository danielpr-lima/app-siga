package br.com.alura.orgs

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY

class MainActivity : Activity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "Bem vindo ao orgs", Toast.LENGTH_SHORT).show()
        val view = TextView(this)
        view.setText("Cesta de futas")

        setContentView(R.layout.activity_main)
    }
}