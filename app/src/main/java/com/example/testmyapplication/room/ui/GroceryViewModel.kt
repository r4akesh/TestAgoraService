package com.example.testmyapplication.room.ui

import androidx.lifecycle.ViewModel
import com.example.testmyapplication.room.GroceryItems
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GroceryViewModel(private val repository: GroceryRepository) : ViewModel() {
 
    // In coroutines thread insert item in insert function.
    fun insert(item: GroceryItems) = GlobalScope.launch {
        repository.insert(item)
    }
 
    // In coroutines thread delete item in delete function.
    fun delete(item: GroceryItems) = GlobalScope.launch {
        repository.delete(item)
    }

    fun update(qty:Int,itemName: String) = GlobalScope.launch {
        repository.update(qty,itemName)
    }
 
    //Here we initialized allGroceryItems function with repository
    fun allGroceryItems() = repository.allGroceryItems()

 
}