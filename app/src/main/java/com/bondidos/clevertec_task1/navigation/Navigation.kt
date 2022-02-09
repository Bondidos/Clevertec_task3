package com.bondidos.clevertec_task1.navigation

import android.os.Bundle

interface Navigation {
    fun navigateFirstFragment()
    fun navigateDetailsFragment(item: Bundle)
    fun onPowerBtnPush()
}