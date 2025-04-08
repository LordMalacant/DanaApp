package com.example.productosapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "empresas")
data class Empresa(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nombre: String
)

