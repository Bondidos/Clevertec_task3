package com.bondidos.clevertec_task1.presentation.fragments.viewModel

import androidx.lifecycle.ViewModel
import com.bondidos.clevertec_task1.domain.model.ItemModel
import com.bondidos.clevertec_task1.domain.usecases.GetContactsUseCase
import javax.inject.Inject

class FirstFragmentViewModel @Inject constructor (private val getContacts: GetContactsUseCase): ViewModel() {

    private var itemList : List<ItemModel>? = null

    init {
        itemList = getContacts.execute()
    }

    fun getItemList() = itemList ?: emptyList()
    fun getItem(id: Int): ItemModel {
        itemList?.let {
            return it[id]
        }
        return ItemModel("null",null,null,null,null)
    }
}
