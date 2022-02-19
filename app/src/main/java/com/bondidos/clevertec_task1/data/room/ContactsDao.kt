package com.bondidos.clevertec_task1.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.bondidos.clevertec_task1.domain.model.ItemModel

@Dao
interface ContactsDao {

    @Insert(onConflict = REPLACE)
    fun saveContact(contact: ItemModel): Long

    @Query("select * from itemModel")
    fun getSavedContacts(): List<ItemModel>
}