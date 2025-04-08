package com.example.productosapp

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.productosapp.database.AppDatabase
import com.example.productosapp.model.Producto
import kotlinx.coroutines.launch

class MostrarProductosActivity : AppCompatActivity() {

    private lateinit var listaProductos: ListView
    private lateinit var db: AppDatabase
    private val productos = mutableListOf<Producto>()
    private lateinit var adaptador: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_productos)

        listaProductos = findViewById(R.id.listaProductos)

        db = AppDatabase.getDatabase(this)

        // Recibir el ID de empresa aquí
        val empresaId = intent.getLongExtra("EMPRESA_ID", -1)

        adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, productos.map { it.nombre })
        listaProductos.adapter = adaptador

        cargarProductos(empresaId)
    }

    private fun cargarProductos(empresaId: Long) {
        lifecycleScope.launch {
            val lista = db.productoDao().obtenerPorEmpresa(empresaId)
            productos.clear()
            productos.addAll(lista)
            adaptador.notifyDataSetChanged()
        }
    }
}

