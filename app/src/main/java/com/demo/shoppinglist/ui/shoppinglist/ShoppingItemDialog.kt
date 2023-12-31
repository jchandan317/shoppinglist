package com.demo.shoppinglist.ui.shoppinglist

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.demo.shoppinglist.R
import com.demo.shoppinglist.data.db.entity.ShoppingItem

class ShoppingItemDialog(context: Context, private var addDialogListener: AddDialogListener) : AppCompatDialog(context) {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContentView(R.layout.dialog_add_shopping_item)

		findViewById<TextView>(R.id.tvAdd)?.setOnClickListener {
			val name = findViewById<EditText>(R.id.etName)?.text.toString()
			val amount = findViewById<EditText>(R.id.etAmount)?.text.toString()

			if (name.isEmpty() || amount.isEmpty())
			{
				Toast.makeText(context, "Please enter all the information", Toast.LENGTH_SHORT).show()
				return@setOnClickListener
			}

			val item = ShoppingItem(name, amount.toInt())
			addDialogListener.onAddButtonClick(item)
			dismiss()
		}

		findViewById<TextView>(R.id.tvCancel)?.setOnClickListener {
			cancel()
		}
	}
}