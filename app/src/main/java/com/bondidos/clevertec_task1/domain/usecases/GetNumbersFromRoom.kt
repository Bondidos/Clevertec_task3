package com.bondidos.clevertec_task1.domain.usecases

import com.bondidos.clevertec_task1.domain.Repository
import com.bondidos.clevertec_task1.domain.state.IsSuccess
import javax.inject.Inject

class GetNumbersFromRoom @Inject constructor(private val dataBase: Repository) {
    suspend fun execute(): IsSuccess {
        return try {
            val data = dataBase.getSavedContacts()
            IsSuccess.SuccessWithData(data)
        } catch (e: Exception) {
            IsSuccess.Error("Can't read from DataBase")
        }
    }
}