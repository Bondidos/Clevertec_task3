package com.bondidos.clevertec_task1.presentation.ui.fragments.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.DialogFragment
import com.bondidos.clevertec_task1.R
import com.bondidos.clevertec_task1.domain.constants.Const.PHONE_NUMBER
import com.bondidos.clevertec_task1.domain.constants.Const.SHARED_PREFS
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import java.lang.IllegalArgumentException

class NumbersDialog constructor(private val list: List<String?>) : DialogFragment() {

    private var coordinatorLayout: CoordinatorLayout? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        coordinatorLayout = activity?.findViewById(R.id.coordinatorLayout)

        return activity?.let {
            AlertDialog.Builder(it)
                .setTitle("Pick a Number")
                .setItems(
                    list.toTypedArray()
                ) { _, which ->
                    val sharedPrefs =
                        activity?.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)

                    sharedPrefs?.let { prefs ->
                        prefs.edit().apply {
                            putString(PHONE_NUMBER, list[which])
                        }.apply()
                    }
                    coordinatorLayout?.let { it1 ->
                        Snackbar.make(
                            it1,
                            "Saving in SP " + list[which],
                            BaseTransientBottomBar.LENGTH_LONG
                        ).show()
                    }
                }
                .create()
        } ?: throw IllegalArgumentException("Activity cannot be null")
    }
}