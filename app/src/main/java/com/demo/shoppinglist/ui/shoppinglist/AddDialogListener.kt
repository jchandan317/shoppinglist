package com.demo.shoppinglist.ui.shoppinglist

import com.demo.shoppinglist.data.db.entity.ShoppingItem

interface AddDialogListener {

	fun onAddButtonClick(item: ShoppingItem)
}