package com.bondidos.clevertec_task1.domain

import com.bondidos.clevertec_task1.domain.model.ItemModel

interface Repository {
    suspend fun saveContact(contact: ItemModel): Long
    suspend fun getSavedContacts(): List<ItemModel>
    suspend fun findItem(number: String): List<ItemModel>
}