package com.example.testmyapplication.room.ui

import com.example.testmyapplication.room.GroceryItems

interface DialogListener {
   
    // Create a function to add items
      // in GroceryItems on clicking
    fun onAddButtonClicked(item: GroceryItems)

    //fun onUpdateButtonClicked(item: GroceryItems)
}