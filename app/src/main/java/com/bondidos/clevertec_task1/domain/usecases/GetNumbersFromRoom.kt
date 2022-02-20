package com.bondidos.clevertec_task1.domain.usecases

import com.bondidos.clevertec_task1.data.room.ContactsDao
import javax.inject.Inject

class GetNumbersFromRoom @Inject constructor(private val dataBase: ContactsDao) {
    fun execute(): List<String?> {
        val data = dataBase.getSavedContacts()
        return data
            .filter { it.number != null && it.number.isNotEmpty() }
            .map { it.number }
    }
}