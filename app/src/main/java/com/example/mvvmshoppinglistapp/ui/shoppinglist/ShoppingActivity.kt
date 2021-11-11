package com.example.mvvmshoppinglistapp.ui.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.mvvmshoppinglistapp.R
import com.example.mvvmshoppinglistapp.data.db.ShoppingDatabase
import com.example.mvvmshoppinglistapp.data.repositories.ShoppingRepository

class ShoppingActivity : AppCompatActivity() {

    private val viewModel: ShoppingViewModel by viewModels { ShoppingViewModelFactory(
        ShoppingRepository(ShoppingDatabase(this))
    ) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)


    }
}