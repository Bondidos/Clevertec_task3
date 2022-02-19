package com.bondidos.clevertec_task1.presentation.navigation

import android.os.Bundle
import com.bondidos.clevertec_task1.domain.model.ItemModel

interface Navigation {
    fun navigateFirstFragment()
    fun navigateHome()
    fun navigateDetailsFragment(item: ItemModel)
    fun onPowerBtnPush()
    fun openListDialog(list: List<String?>)
}