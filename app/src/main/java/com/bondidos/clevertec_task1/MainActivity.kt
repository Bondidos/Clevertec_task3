package com.bondidos.clevertec_task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bondidos.clevertec_task1.fragments.DetailsFragment
import com.bondidos.clevertec_task1.navigation.Navigation

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