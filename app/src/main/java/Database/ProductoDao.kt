package com.example.productosapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.productosapp.model.Producto

@Dao
interface ProductoDao {
    @Insert
    suspend fun insertar(producto: Producto)

    @Query("SELECT * FROM productos WHERE empresaId = :empresaId")
    suspend fun obtenerPorEmpresa(empresaId: Long): List<Producto>
}