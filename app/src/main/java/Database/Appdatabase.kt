package com.example.productosapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.productosapp.model.Empresa
import com.example.productosapp.model.Producto

@Database(entities = [Empresa::class, Producto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun empresaDao(): EmpresaDao
    abstract fun productoDao(): ProductoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "productos_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

