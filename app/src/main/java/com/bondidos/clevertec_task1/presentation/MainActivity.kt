package com.bondidos.clevertec_task1.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bondidos.clevertec_task1.ExitFragment
import com.bondidos.clevertec_task1.R
import com.bondidos.clevertec_task1.domain.model.ItemModel
import com.bondidos.clevertec_task1.presentation.fragments.DetailsFragment
import com.bondidos.clevertec_task1.presentation.fragments.FirstFragment
import com.bondidos.clevertec_task1.presentation.fragments.StartScreen
import com.bondidos.clevertec_task1.presentation.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main), Navigation {

    override fun navigateFirstFragment() {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.animator.flip_in,
                R.animator.flip_out,
                R.animator.flip_in,
                R.animator.flip_out
            )
            .replace(R.id.container,FirstFragment())
            .commit()
    }

    override fun navigateHome() {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.animator.flip_in,
                R.animator.flip_out,
                R.animator.flip_in,
                R.animator.flip_out
            )
            .replace(R.id.container,StartScreen())
            .commit()
    }

    override fun navigateDetailsFragment(item: ItemModel) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.animator.flip_in,
                R.animator.flip_out,
                R.animator.flip_in,
                R.animator.flip_out
            )
            .replace(R.id.container, DetailsFragment.newInstance(item))
            .commit()
    }

    override fun onPowerBtnPush() = onBackPressed()

    override fun onBackPressed() {
        ExitFragment()
            .show(supportFragmentManager, "Exit?")
    }
}