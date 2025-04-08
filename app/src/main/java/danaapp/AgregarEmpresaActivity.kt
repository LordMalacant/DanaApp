package com.example.productosapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import com.example.productosapp.database.AppDatabase
import com.example.productosapp.model.Empresa
import kotlinx.coroutines.launch

class AgregarEmpresaActivity : AppCompatActivity() {

    private lateinit var editTextNombreEmpresa: EditText
    private lateinit var botonRegistrar: Button
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_empresa)

        editTextNombreEmpresa = findViewById(R.id.editTextNombreEmpresa)
        botonRegistrar = findViewById(R.id.botonRegistrar)

        db = AppDatabase.getDatabase(this)

        botonRegistrar.setOnClickListener {
            val nombre = editTextNombreEmpresa.text.toString()
            val empresa = Empresa(nombre = nombre)
            guardarEmpresa(empresa)
        }
    }

    private fun guardarEmpresa(empresa: Empresa) {
        lifecycleScope.launch {
            db.empresaDao().insertar(empresa)
            finish()
        }
    }
}