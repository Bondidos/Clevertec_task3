package com.bondidos.clevertec_task1

import com.bondidos.clevertec_task1.data.room.ContactsDao
import com.bondidos.clevertec_task1.model.ItemModel
import javax.inject.Inject

class SaveContactUseCase @Inject constructor(private val dataBase: ContactsDao){
    fun execute(item: ItemModel){
        dataBase.saveContact(item)
    }
}