package com.bondidos.clevertec_task1.presentation.ui.navigation

import com.bondidos.clevertec_task1.domain.model.ItemModel

interface Navigation {
    fun navigateFirstFragment()
    fun navigateHome()
    fun navigateDetailsFragment(item: ItemModel)
    fun onPowerBtnPush()
    fun openListDialog(list: List<String?>)
}