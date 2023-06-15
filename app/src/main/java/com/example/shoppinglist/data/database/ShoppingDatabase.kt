package com.example.shoppinglist.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoppinglist.data.database.entities.ShoppingItem

@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingDatabase: RoomDatabase() {

    abstract fun getShoppingDao(): ShoppingDAO              //Através desta função, acessamos as operações da BD da classe DAO

    companion object{             //é o mesmo que a palavra reservada "static" do Java, isso é tudo dentro do "companion object" é estático.f
        @Volatile              // Por investigar!!!
        private var instance: ShoppingDatabase? = null              //variável do tipo da classe da BD, inicialmente nula.
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){ // Sempre que criar uma instância desta classe(Ex: ShoppingDatabase()), esta função será ivocada.
            instance ?: createDataBase(context).also { instance = it } //Quando chamado o bloco do synchronized(LOCK){}, nenhuma outra thread alem da principal fará set a instância quando executar o código no bloco.
        }

        private fun createDataBase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
            ShoppingDatabase::class.java, "ShoppingDB.db").build()
    }
}