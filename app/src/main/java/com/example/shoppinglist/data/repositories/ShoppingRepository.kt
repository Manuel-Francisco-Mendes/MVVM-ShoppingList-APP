package com.example.shoppinglist.data.repositories

import com.example.shoppinglist.data.database.ShoppingDatabase
import com.example.shoppinglist.data.database.entities.ShoppingItem
class ShoppingRepository(private val database: ShoppingDatabase) {

    suspend fun upsert(item: ShoppingItem) = database.getShoppingDao().upsert(item)


    suspend fun delete(item: ShoppingItem) = database.getShoppingDao().delete(item)


    fun getAllShoppingItems() = database.getShoppingDao().getAllShoppingItems()

}