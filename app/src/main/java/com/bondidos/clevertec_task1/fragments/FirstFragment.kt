package com.bondidos.clevertec_task1.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bondidos.clevertec_task1.recycler.Adapter
import com.bondidos.clevertec_task1.constants.Const.DESCRIPTION
import com.bondidos.clevertec_task1.constants.Const.IMAGE
import com.bondidos.clevertec_task1.constants.Const.TITLE
import com.bondidos.clevertec_task1.MainActivity
import com.bondidos.clevertec_task1.navigation.Navigation
import com.bondidos.clevertec_task1.databinding.FirstFragmentBinding
import com.bondidos.clevertec_task1.fragments.viewModel.FirstFragmentViewModel

class FirstFragment : Fragment() {

    private var _binding: FirstFragmentBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val viewModel by lazy { FirstFragmentViewModel() }
    private var navigation: Navigation? = null

    private val itemAdapter: Adapter by lazy {

        Adapter {
            val item = viewModel.getItem(it)
            val bundle = Bundle().apply {
                putString(IMAGE, item.image)
                putString(TITLE, item.title)
                putString(DESCRIPTION, item.description)
            }
            navigation?.navigateDetailsFragment(bundle)
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