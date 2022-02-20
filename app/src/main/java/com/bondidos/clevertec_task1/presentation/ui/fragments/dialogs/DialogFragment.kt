package com.bondidos.clevertec_task1.presentation.ui.fragments.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bondidos.clevertec_task1.databinding.FragmentDialogBinding
import com.bondidos.clevertec_task1.presentation.ui.MainActivity

class ExitFragment : DialogFragment() {

    private var _binding: FragmentDialogBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnYes.setOnClickListener { (activity as MainActivity).finish() }
            btnNo.setOnClickListener { dismiss() }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}