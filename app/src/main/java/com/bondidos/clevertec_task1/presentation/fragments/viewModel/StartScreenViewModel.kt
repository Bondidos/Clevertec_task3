package com.bondidos.clevertec_task1.presentation.fragments.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bondidos.clevertec_task1.domain.model.ItemModel
import com.bondidos.clevertec_task1.domain.usecases.GetNumbersFromRoom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class StartScreenViewModel @Inject constructor(
    private val getNumbers: GetNumbersFromRoom
) : ViewModel(){

    private var numberList : List<String?>? = null

    init {
        viewModelScope.launch(Dispatchers.IO) {
            numberList = getNumbers.execute()
        }
    }

    fun getItemList() = numberList ?: emptyList()
}