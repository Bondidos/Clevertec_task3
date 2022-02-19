package com.bondidos.clevertec_task1.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bondidos.clevertec_task1.ExitFragment
import com.bondidos.clevertec_task1.R
import com.bondidos.clevertec_task1.presentation.fragments.DetailsFragment
import com.bondidos.clevertec_task1.presentation.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main), Navigation {

    override fun navigateFirstFragment() {
        supportFragmentManager
            .popBackStack()
    }

    override fun navigateDetailsFragment(item: Bundle) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.animator.flip_in,
                R.animator.flip_out,
                R.animator.flip_in,
                R.animator.flip_out
            )
            .addToBackStack("First")
            .replace(R.id.container, DetailsFragment.newInstance(item))
            .commit()
    }

    override fun onPowerBtnPush() = onBackPressed()

    override fun onBackPressed() {
        ExitFragment()
            .show(supportFragmentManager, "Exit?")
    }
}