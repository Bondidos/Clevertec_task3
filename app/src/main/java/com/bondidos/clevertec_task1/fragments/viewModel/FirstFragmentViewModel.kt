package com.bondidos.clevertec_task1.fragments.viewModel

import androidx.lifecycle.ViewModel
import com.bondidos.clevertec_task1.model.ItemModel

class FirstFragmentViewModel : ViewModel() {

    private val itemList = List(1000) {
        ItemModel(
            image = "Place for URL#${it+1}",
            title = "Title ${it+1}",
            description = "Description ${it+1}"
        )
    }

    fun getItemList() = itemList
    fun getItem(id: Int) = itemList[id]
}
