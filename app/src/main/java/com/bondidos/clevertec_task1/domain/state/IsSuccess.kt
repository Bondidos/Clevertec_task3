package com.bondidos.clevertec_task1.domain.state

import com.bondidos.clevertec_task1.domain.model.ItemModel

sealed class IsSuccess {
    object Initialized : IsSuccess()
    object Success : IsSuccess()
    object Loading : IsSuccess()
    data class SuccessWithData(
        val data: List<ItemModel>
    ) : IsSuccess()

    data class Error(
        val message: String
    ) : IsSuccess()
}