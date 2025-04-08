package com.example.productosapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.productosapp.model.Empresa

@Dao
interface EmpresaDao {
    @Insert
    suspend fun insertar(empresa: Empresa)

    @Query("SELECT * FROM empresas")
    suspend fun obtenerTodas(): List<Empresa>
}