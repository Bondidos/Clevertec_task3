package com.bondidos.clevertec_task1.presentation.fragments.viewModel

import com.bondidos.clevertec_task1.domain.SaveContactUseCase
import com.bondidos.clevertec_task1.domain.model.ItemModel
import javax.inject.Inject

class DetailFragmentViewModel @Inject constructor(private val saveContact: SaveContactUseCase) {
    fun saveContact(item: ItemModel){
        saveContact.execute(item)
    }
}