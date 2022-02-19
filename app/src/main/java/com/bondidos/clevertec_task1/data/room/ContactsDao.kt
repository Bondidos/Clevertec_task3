package com.bondidos.clevertec_task1.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.bondidos.clevertec_task1.domain.model.ItemModel

@Dao
interface ContactsDao {

    @Insert
    fun saveContact(contact: ItemModel)

    @Query("select * from itemModel")
    fun getSavedContacts(): List<ItemModel>
}