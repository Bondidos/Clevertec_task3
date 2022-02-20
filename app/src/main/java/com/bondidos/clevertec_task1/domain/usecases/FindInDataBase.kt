package com.bondidos.clevertec_task1.domain.usecases

import com.bondidos.clevertec_task1.domain.Repository
import com.bondidos.clevertec_task1.domain.state.IsSuccess
import java.lang.Exception
import javax.inject.Inject

class FindInDataBase @Inject constructor(private val dataBase: Repository) {

    suspend fun execute(number: String): IsSuccess {
        return try {
            val result = dataBase.findItem(number)
            IsSuccess.SuccessWithData(result)
        } catch (e: Exception) {
            IsSuccess.Error("Cant find contact")
        }
    }
}