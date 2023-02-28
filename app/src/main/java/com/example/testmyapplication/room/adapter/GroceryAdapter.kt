package com.example.testmyapplication.room.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testmyapplication.R
import com.example.testmyapplication.room.GroceryItems
import com.example.testmyapplication.room.ui.GroceryViewModel
import kotlinx.android.synthetic.main.raw_room.view.*

class GroceryAdapter(var list: List<GroceryItems>, val viewModel: GroceryViewModel) :
    RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder>() {
 
    // In this function we will add our groceryadapter.xml to kotlin class
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.raw_room, parent, false)
        return GroceryViewHolder(view)
    }
 
    // This function is used to return total number of size of list.
    override fun getItemCount(): Int {
        return list.size
    }
 
    // In onBindViewHolder we will bind our itemViews with adapter
    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        var currentPosition = list[position]
        holder.itemView.txtItemName.text = currentPosition.itemName
        holder.itemView.txtItemPrice.text = "${currentPosition.itemPrice}"
        holder.itemView.txtItemQuantity.text = "${currentPosition.itemQuantity}"
        holder.itemView.ibDelete.setOnClickListener {
           // viewModel.delete(currentPosition)
            viewModel.update(10,currentPosition.itemName)
        }
 
        // To get total cost
        if (position == list.size - 1) {
            var totalCost = 0
            for (i in 0 until list.size) {
                totalCost += list[i].itemPrice
            }
            holder.itemView.txtItemTotalCost.visibility = View.VISIBLE
            holder.itemView.txtTotalCostTitle.visibility = View.VISIBLE
            holder.itemView.txtItemTotalCost.text = "$totalCost"
        }
    }
    // Inner class for viewHolder
    inner class GroceryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}