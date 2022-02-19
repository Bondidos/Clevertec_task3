package com.bondidos.clevertec_task1.domain

import com.bondidos.clevertec_task1.data.room.ContactsDao
import com.bondidos.clevertec_task1.domain.model.ItemModel
import com.bondidos.clevertec_task1.domain.state.IsSuccess
import java.lang.Exception
import javax.inject.Inject

class SaveContactUseCase @Inject constructor(private val dataBase: ContactsDao){
    fun execute(item: ItemModel): IsSuccess{
        return try{
            dataBase.saveContact(item)
            IsSuccess.Success
        } catch (e: Exception){
            IsSuccess.Error("Adding Fail")
        }
    }
}