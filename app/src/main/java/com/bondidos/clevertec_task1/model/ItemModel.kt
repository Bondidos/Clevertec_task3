package com.bondidos.clevertec_task1.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemModel(
    @PrimaryKey
    val id: String,
    val image: String? = null,
    val name: String?,
    val number: String?,
    val email: String?
)