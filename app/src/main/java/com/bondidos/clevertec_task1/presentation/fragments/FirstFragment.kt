package com.bondidos.clevertec_task1.presentation.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bondidos.clevertec_task1.presentation.recycler.Adapter
import com.bondidos.clevertec_task1.presentation.MainActivity
import com.bondidos.clevertec_task1.presentation.navigation.Navigation
import com.bondidos.clevertec_task1.databinding.FirstFragmentBinding
import com.bondidos.clevertec_task1.presentation.fragments.viewModel.FirstFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
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
            navigation?.navigateDetailsFragment(viewModel.getItem(it))
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
        initRecycler()
    }

    private fun initRecycler() {

        itemAdapter.setList(viewModel.getItemList())

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