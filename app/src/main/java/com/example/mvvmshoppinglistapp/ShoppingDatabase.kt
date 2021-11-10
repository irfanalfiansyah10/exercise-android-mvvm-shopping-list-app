package com.example.mvvmshoppinglistapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingDatabase : RoomDatabase() {

    companion object {
        private val _lock = Any()

        @Volatile
        private var _instance: ShoppingDatabase? = null

        private fun _createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ShoppingDatabase::class.java,
            "ShoppingDB.db"
        ).build()

        operator fun invoke(context: Context) = _instance ?: synchronized(_lock) {
            _instance ?: _createDatabase(context).also { _instance = it }
        }
    }

    abstract fun getShoppingDao(): ShoppingDao

}