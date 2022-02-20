package com.bondidos.clevertec_task1.presentation.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bondidos.clevertec_task1.presentation.ui.fragments.dialogs.ExitFragment
import com.bondidos.clevertec_task1.R
import com.bondidos.clevertec_task1.domain.model.ItemModel
import com.bondidos.clevertec_task1.presentation.ui.fragments.detailsScreen.DetailsFragment
import com.bondidos.clevertec_task1.presentation.ui.fragments.contactsScreen.FirstFragment
import com.bondidos.clevertec_task1.presentation.ui.fragments.dialogs.NumbersDialog
import com.bondidos.clevertec_task1.presentation.ui.fragments.startScreen.StartScreen
import com.bondidos.clevertec_task1.presentation.ui.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main), Navigation {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkPermissions(this)
    }

    override fun navigateFirstFragment() {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.animator.flip_in,
                R.animator.flip_out,
                R.animator.flip_in,
                R.animator.flip_out
            )
            .replace(R.id.container, FirstFragment())
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
            .replace(R.id.container, StartScreen())
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
    override fun openListDialog(list: List<String?>) {
        NumbersDialog(list).show(supportFragmentManager, "List")
    }

    override fun onBackPressed() {
        ExitFragment()
            .show(supportFragmentManager, "Exit?")
    }

    private fun checkPermissions(context: Context) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            //request permission
            ActivityCompat.requestPermissions(
                context as MainActivity,
                arrayOf(Manifest.permission.READ_CONTACTS),
                100
            )
        } /*else {
        val contactList = getContactList(context)
        Log.d("Main",contactList.toString())
    }*/
    }
}