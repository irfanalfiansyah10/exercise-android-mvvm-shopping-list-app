package com.example.mvvmshoppinglistapp.ui.shoppinglist

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmshoppinglistapp.R
import com.example.mvvmshoppinglistapp.adapters.ShoppingItemAdapter
import com.example.mvvmshoppinglistapp.data.db.entities.ShoppingItem
import kotlinx.android.synthetic.main.activity_shopping.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ShoppingActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory: ShoppingViewModelFactory by instance()

    private val viewModel: ShoppingViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val adapter = ShoppingItemAdapter(listOf(), viewModel)
        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter

        viewModel.getAllShoppingItems().observe(this) {
            adapter.items = it
            adapter.notifyDataSetChanged()
        }

        fab.setOnClickListener {
            AddShoppingItemDialog(this, object : AddDialogListener {
                override fun onAddButtonClick(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }
    }
}