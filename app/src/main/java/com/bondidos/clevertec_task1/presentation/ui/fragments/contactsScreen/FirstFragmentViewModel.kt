package com.bondidos.clevertec_task1.presentation.ui.fragments.contactsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bondidos.clevertec_task1.domain.state.IsSuccess
import com.bondidos.clevertec_task1.domain.usecases.GetContactsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FirstFragmentViewModel @Inject constructor(getContacts: GetContactsUseCase) : ViewModel() {

    private val _itemList = MutableStateFlow<IsSuccess>(IsSuccess.Initialized)
    val itemList: StateFlow<IsSuccess> = _itemList.asStateFlow()

    init {
        _itemList.value = IsSuccess.Loading
        viewModelScope.launch(Dispatchers.IO) {
            _itemList.value = getContacts.execute()
        }
    }
}