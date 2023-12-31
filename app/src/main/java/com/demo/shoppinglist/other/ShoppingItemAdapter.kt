package com.demo.shoppinglist.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.shoppinglist.R
import com.demo.shoppinglist.data.db.entity.ShoppingItem
import com.demo.shoppinglist.ui.shoppinglist.ShoppingViewModel

class ShoppingItemAdapter(
	var items: List<ShoppingItem>,
	private var viewModel: ShoppingViewModel
): RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
		return ShoppingViewHolder(view)
	}

	override fun getItemCount(): Int {
		return items.size
	}

	override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
		val currentShoppingItem = items.get(position)
		holder.itemView.findViewById<TextView>(R.id.tvName).setText(currentShoppingItem.name)
		holder.itemView.findViewById<TextView>(R.id.tvAmount).setText("${currentShoppingItem.amount}")

		holder.itemView.findViewById<ImageView>(R.id.ivDelete).setOnClickListener {
			viewModel.delete(currentShoppingItem)
		}

		holder.itemView.findViewById<ImageView>(R.id.ivPlus).setOnClickListener {
			currentShoppingItem.amount++
			viewModel.upsert(currentShoppingItem)
		}

		holder.itemView.findViewById<ImageView>(R.id.ivMinus).setOnClickListener {
			currentShoppingItem.amount--
			if (currentShoppingItem.amount <= 0)
				viewModel.delete(currentShoppingItem)
			viewModel.upsert(currentShoppingItem)
		}

	}


	inner class ShoppingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)


}