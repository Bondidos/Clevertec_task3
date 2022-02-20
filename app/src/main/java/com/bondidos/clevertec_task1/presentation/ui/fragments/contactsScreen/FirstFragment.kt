package com.bondidos.clevertec_task1.presentation.ui.fragments.contactsScreen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bondidos.clevertec_task1.databinding.FirstFragmentBinding
import com.bondidos.clevertec_task1.domain.state.IsSuccess
import com.bondidos.clevertec_task1.presentation.ui.MainActivity
import com.bondidos.clevertec_task1.presentation.ui.navigation.Navigation
import com.bondidos.clevertec_task1.presentation.ui.recycler.Adapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class FirstFragment : Fragment() {

    private var _binding: FirstFragmentBinding? = null
    private val binding get() = requireNotNull(_binding)
    private var navigation: Navigation? = null

    @Inject
    lateinit var viewModel: FirstFragmentViewModel

    private val itemAdapter: Adapter by lazy {

        Adapter {
            navigation?.navigateDetailsFragment(it)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigation = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FirstFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {

        lifecycleScope.launchWhenCreated {
            viewModel.itemList.collect { state ->
                when (state) {
                    is IsSuccess.Loading -> binding.progressBar.isVisible = true
                    is IsSuccess.SuccessWithData -> {
                        binding.progressBar.isVisible = false
                        itemAdapter.setList(state.data)
                    }
                    is IsSuccess.Error -> {
                        binding.progressBar.isVisible = false
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
                    }
                    else -> Unit
                }
            }
        }

        binding.recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = itemAdapter
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}