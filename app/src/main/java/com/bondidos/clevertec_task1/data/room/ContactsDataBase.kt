package com.bondidos.clevertec_task1.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bondidos.clevertec_task1.domain.model.ItemModel

@Database(entities = [ItemModel::class], version = 1)
abstract class ContactsDataBase : RoomDatabase() {
    abstract fun contactsDB(): ContactsDao
}