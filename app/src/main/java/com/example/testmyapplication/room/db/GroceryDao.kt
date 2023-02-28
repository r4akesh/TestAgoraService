package com.example.testmyapplication.room.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.testmyapplication.room.GroceryItems

// This class is used to create
// function for database.
@Dao
interface GroceryDao {
 
    // Insert function is used to
    // insert data in database.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: GroceryItems)
 
    // Delete function is used to
    // delete data in database.
    @Delete
    suspend fun delete(item: GroceryItems)


    @Query("UPDATE grocery_items SET itemQuantity=:qtyy WHERE itemName =:itemNamee")
    fun updateUser(qtyy: String?,   itemNamee: String?): Int
 
    // getAllGroceryItems function is used to get
    // all the data of database.
    @Query("SELECT * FROM grocery_items")
    fun getAllGroceryItems(): LiveData<List<GroceryItems>>
}