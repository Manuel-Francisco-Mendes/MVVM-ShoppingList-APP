package com.example.shoppinglist.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.data.database.entities.ShoppingItem
import com.example.shoppinglist.ui.shoppingList.ShoppingViewModel

class ShoppingItemAdapter(var itemsList: List<ShoppingItem>, private val viewModel: ShoppingViewModel): RecyclerView.Adapter<ShoppingItemAdapter.ShoppingListViewHolder>() {

    inner class ShoppingListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tVItemName = itemView.findViewById<TextView>(R.id.tvName)
        var tVItemAmount = itemView.findViewById<TextView>(R.id.tvAmount)
        var imgVDelete = itemView.findViewById<ImageView>(R.id.ivDelete)
        var imgVMinus = itemView.findViewById<ImageView>(R.id.ivMinus)
        var imgVPlus = itemView.findViewById<ImageView>(R.id.ivPlus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item,parent,false)
        return ShoppingListViewHolder((view))
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        var currentShoppingItem = itemsList[position]

        holder.tVItemName.text = currentShoppingItem.name
        holder.tVItemAmount.text = currentShoppingItem.amount.toString()

        holder.imgVDelete.setOnClickListener {
            viewModel.delete(currentShoppingItem)
        }

        holder.imgVMinus.setOnClickListener {
            if(currentShoppingItem.amount > 0){
                currentShoppingItem.amount--
                viewModel.upsert(currentShoppingItem)
            }
        }

        holder.imgVPlus.setOnClickListener {
            currentShoppingItem.amount++
            viewModel.upsert(currentShoppingItem)
        }
    }
}