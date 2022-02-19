package com.bondidos.clevertec_task1.fragments.viewModel

import androidx.lifecycle.ViewModel
import com.bondidos.clevertec_task1.GetContactsUseCase
import com.bondidos.clevertec_task1.model.ItemModel
import javax.inject.Inject

class FirstFragmentViewModel @Inject constructor (private val getContacts: GetContactsUseCase): ViewModel() {

    private var itemList : List<ItemModel>? = null
        /*List(1000) {
        ItemModel(
            name = "Place for URL#${it+1}",
            title = "Title ${it+1}",
            description = "Description ${it+1}"
        )
    }*/
    init {
        itemList = getContacts.execute()
    }

    fun getItemList() = itemList ?: emptyList()
    fun getItem(id: Int): ItemModel {
        itemList?.let {
            return it[id]
        }
        return ItemModel(null,null,null,null,null)
    }
}
