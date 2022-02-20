package com.bondidos.clevertec_task1.presentation.ui.fragments.startScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bondidos.clevertec_task1.domain.state.IsSuccess
import com.bondidos.clevertec_task1.domain.usecases.FindInDataBase
import com.bondidos.clevertec_task1.domain.usecases.GetNumbersFromRoom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class StartScreenViewModel @Inject constructor(
    private val getNumbers: GetNumbersFromRoom,
    private val findContact: FindInDataBase
) : ViewModel() {

    private val _numberList = MutableStateFlow<IsSuccess>(IsSuccess.Initialized)
    val numberList: StateFlow<IsSuccess> = _numberList.asStateFlow()

    private val _contact = MutableStateFlow<IsSuccess>(IsSuccess.Initialized)
    val contact: StateFlow<IsSuccess> = _contact.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _numberList.value = getNumbers.execute()
        }
    }

    fun findContact(number: String) {
        _contact.value = IsSuccess.Initialized
        viewModelScope.launch(Dispatchers.IO) {
            _contact.value = findContact.execute(number)
        }
    }
}