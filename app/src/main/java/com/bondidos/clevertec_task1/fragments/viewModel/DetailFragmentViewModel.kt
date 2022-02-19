package com.bondidos.clevertec_task1.fragments.viewModel

import com.bondidos.clevertec_task1.SaveContactUseCase
import com.bondidos.clevertec_task1.model.ItemModel
import javax.inject.Inject

class DetailFragmentViewModel @Inject constructor(private val saveContact: SaveContactUseCase) {
    fun saveContact(item: ItemModel){
        saveContact.execute(item)
    }
}