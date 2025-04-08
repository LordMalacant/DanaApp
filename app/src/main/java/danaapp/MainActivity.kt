package com.example.productosapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.ArrayAdapter
import androidx.lifecycle.lifecycleScope
import com.example.productosapp.database.AppDatabase
import com.example.productosapp.model.Empresa
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var listaEmpresas: ListView
    private lateinit var botonAgregarEmpresa: Button
    private lateinit var db: AppDatabase
    private lateinit var adaptador: ArrayAdapter<String>
    private val empresas = mutableListOf<Empresa>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listaEmpresas = findViewById(R.id.listaEmpresas)
        botonAgregarEmpresa = findViewById(R.id.botonAgregarEmpresa)

        db = AppDatabase.getDatabase(this)

        adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, empresas.map { it.nombre })
        listaEmpresas.adapter = adaptador

        botonAgregarEmpresa.setOnClickListener {
            // Navegar a la actividad de agregar empresa
            startActivity(Intent(this, AgregarEmpresaActivity::class.java))
        }

        cargarEmpresas()
    }

    private fun cargarEmpresas() {
        lifecycleScope.launch {
            val lista = db.empresaDao().obtenerTodas()
            empresas.clear()
            empresas.addAll(lista)
            adaptador.notifyDataSetChanged()
        }
    }
}