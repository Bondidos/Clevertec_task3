package com.bondidos.clevertec_task1

import android.os.Bundle

interface Navigation {
    fun navigateFirstFragment()
    fun navigateDetailsFragment(item: Bundle)
}