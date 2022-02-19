package com.bondidos.clevertec_task1.domain.state

sealed class IsSuccess {
    object Initialized : IsSuccess()
    object Success: IsSuccess()
    data class Error(
        val message: String
    ): IsSuccess()
}