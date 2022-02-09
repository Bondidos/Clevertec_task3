package com.bondidos.clevertec_task1.fragments.viewModel

import androidx.lifecycle.ViewModel
import com.bondidos.clevertec_task1.model.ItemModel

class FirstFragmentViewModel : ViewModel() {

    private val itemList = List(1000) {
        ItemModel(
            image = "Place for URL#$it",
            title = "Title$it",
            description = "Description$it"
        )
    }

    fun getItemList() = itemList
    fun getItem(id: Int) = itemList[id]
}
