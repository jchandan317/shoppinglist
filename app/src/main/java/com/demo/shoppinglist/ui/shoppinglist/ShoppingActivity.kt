package com.demo.shoppinglist.ui.shoppinglist

import android.content.res.Resources.Theme
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.shoppinglist.R
import com.demo.shoppinglist.data.db.ShoppingDatabase
import com.demo.shoppinglist.data.db.entity.ShoppingItem
import com.demo.shoppinglist.data.repositories.ShoppingRepository
import com.demo.shoppinglist.databinding.ActivityShoppingBinding
import com.demo.shoppinglist.other.ShoppingItemAdapter
import com.demo.shoppinglist.ui.theme.ShoppingListTheme
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

class ShoppingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory =  ShoppingViewModelFactory(repository)

        val viewModel = ViewModelProvider(this,factory).get(ShoppingViewModel::class.java)

        LinearLayoutManager(this).also { this.findViewById<RecyclerView>(R.id.rvShoppingItems).layoutManager = it }

        val adapter = ShoppingItemAdapter(listOf(), viewModel)
        findViewById<RecyclerView>(R.id.rvShoppingItems).adapter = adapter

        viewModel.getAllShoppingList().observe(this, Observer{
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            ShoppingItemDialog(
                this,
                object : AddDialogListener{
                    override fun onAddButtonClick(item: ShoppingItem) {
                        viewModel.upsert(item)
                    }
                }
            ).show()
        }
    }
}