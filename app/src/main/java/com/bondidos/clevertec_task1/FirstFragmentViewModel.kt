package com.bondidos.clevertec_task1
import android.util.Log
import androidx.lifecycle.ViewModel

class FirstFragmentViewModel: ViewModel() {

    private val itemList = List<ItemModel>(1000) {
        ItemModel(
            image = "Place for URL#$it",
            title = "Title$it",
            description = "Description$it"
        )
    }

    private var scrollPosition = 0

    fun setScrollPosition(pos: Int) {
        scrollPosition = pos
    }

    fun getScrollPosition() = scrollPosition

    fun getItemList() = itemList
    fun getItem(id: Int) = itemList[id]

}


