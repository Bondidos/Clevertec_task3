package com.bondidos.clevertec_task1.presentation.fragments.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bondidos.clevertec_task1.domain.SaveContactUseCase
import com.bondidos.clevertec_task1.domain.model.ItemModel
import com.bondidos.clevertec_task1.domain.state.IsSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailFragmentViewModel @Inject constructor(
    private val saveContact: SaveContactUseCase
    ): ViewModel() {

    private val _isAdded = MutableStateFlow<IsSuccess>(IsSuccess.Initialized)
    val isAdded: StateFlow<IsSuccess> = _isAdded.asStateFlow()

    fun saveContact(item: ItemModel){
        _isAdded.value = IsSuccess.Initialized
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                 _isAdded.value = saveContact.execute(item)
            }
        }
    }
}