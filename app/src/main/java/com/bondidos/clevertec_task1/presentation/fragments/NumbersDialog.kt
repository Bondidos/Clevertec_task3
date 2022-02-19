package com.bondidos.clevertec_task1.presentation.fragments

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.bondidos.clevertec_task1.databinding.DialogWithNumbersBinding
import com.bondidos.clevertec_task1.databinding.FragmentDialogBinding
import com.bondidos.clevertec_task1.presentation.MainActivity
import java.lang.IllegalArgumentException

class NumbersDialog constructor(private val list: List<String?>): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            AlertDialog.Builder(it)
                .setTitle("Pick a Number")
                .setItems(list.toTypedArray(),
                    DialogInterface.OnClickListener { dialog, which ->
                        Toast.makeText(it,which.toString(), Toast.LENGTH_LONG).show()
                    }
                )
                .create()
        } ?: throw IllegalArgumentException("Activity cannot be null")
    }
}