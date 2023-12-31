package com.demo.shoppinglist.data.repositories

import com.demo.shoppinglist.data.db.ShoppingDatabase
import com.demo.shoppinglist.data.db.entity.ShoppingItem

class ShoppingRepository(
    private val db: ShoppingDatabase
) {
    suspend fun upsert(item: ShoppingItem) = db.getShoppingDao().upsert(item)

    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)

    fun getAllShoppingItems() = db.getShoppingDao().getAllShoppingItems()
}