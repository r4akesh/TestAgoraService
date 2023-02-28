package com.example.testmyapplication.room.ui

import com.example.testmyapplication.room.GroceryItems
import com.example.testmyapplication.room.db.GroceryDatabase

class GroceryRepository(private val db: GroceryDatabase) {
 
    suspend fun insert(item: GroceryItems) = db.getGroceryDao().insert(item)
    suspend fun delete(item: GroceryItems) = db.getGroceryDao().delete(item)
      fun update(qty: Int,itemName:String) = db.getGroceryDao().updateUser(qty.toString(), itemName)

    fun allGroceryItems() = db.getGroceryDao().getAllGroceryItems()
}