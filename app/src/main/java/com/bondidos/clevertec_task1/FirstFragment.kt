package com.bondidos.clevertec_task1

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bondidos.clevertec_task1.Const.DESCRIPTION
import com.bondidos.clevertec_task1.Const.IMAGE
import com.bondidos.clevertec_task1.Const.TITLE
import com.bondidos.clevertec_task1.databinding.FirstFragmentBinding

class FirstFragment : Fragment() {

    private var _binding: FirstFragmentBinding? = null
    private val binding get() = requireNotNull(_binding)
    private var id: Int? = null
    private val viewModel by lazy { FirstFragmentViewModel() }
    private var navigation: Navigation? = null

    private val itemAdapter: Adapter by lazy {

        Adapter {
            val item = viewModel.getItem(it)
            val bundle = Bundle().apply {
                putString(IMAGE,item.image)
                putString(TITLE,item.title)
                putString(DESCRIPTION,item.description)
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

        binding.recycler.scrollToPosition(viewModel.getScrollPosition())

    }

    override fun onDestroy() {
        viewModel.setScrollPosition(itemAdapter.adapterPosition)
        _binding = null
        super.onDestroy()
    }
}