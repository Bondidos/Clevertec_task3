package com.bondidos.clevertec_task1.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.bondidos.clevertec_task1.domain.Repository
import com.bondidos.clevertec_task1.domain.model.ItemModel

@Dao
interface ContactsDao: Repository {

    @Insert(onConflict = REPLACE)
    override suspend fun saveContact(contact: ItemModel): Long

    @Query("select * from itemModel")
    override suspend fun getSavedContacts(): List<ItemModel>

    // List<itemModel> because of in contacts may be 2+ different contacts with same number
    @Query("select * from itemModel where number = :number")
    override suspend fun findItem(number: String): List<ItemModel>
}