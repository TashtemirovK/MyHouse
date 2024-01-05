package com.example.myhouse.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myhouse.data.local.models.Contact

@Database(entities = [Contact::class], [], 1)
abstract class ContactDataBase: RoomDatabase() {

    abstract fun getDao() : ContactDao
}