package com.bondidos.clevertec_task1.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bondidos.clevertec_task1.databinding.StartScreenFragmentBinding
import com.bondidos.clevertec_task1.presentation.fragments.viewModel.StartScreenViewModel
import com.bondidos.clevertec_task1.presentation.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StartScreen : Fragment(){
    private var _binding: StartScreenFragmentBinding? = null
    private val binding get() = requireNotNull(_binding)
    private var navigation: Navigation? = null

    @Inject
    lateinit var viewModel: StartScreenViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigation = context as Navigation
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = StartScreenFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpListeners()
    }

    private fun setUpListeners() {
        with(binding){
            showContacts.setOnClickListener { navigation?.navigateFirstFragment() }
            selectContact.setOnClickListener { navigation?.openListDialog(viewModel.getItemList()) }
        }
    }
    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}