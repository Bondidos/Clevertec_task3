package com.bondidos.clevertec_task1.presentation.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bondidos.clevertec_task1.domain.constants.Const.DESCRIPTION
import com.bondidos.clevertec_task1.domain.constants.Const.IMAGE
import com.bondidos.clevertec_task1.domain.constants.Const.TITLE
import com.bondidos.clevertec_task1.domain.model.ItemModel
import com.bondidos.clevertec_task1.presentation.MainActivity
import com.bondidos.clevertec_task1.presentation.navigation.Navigation
import com.bondidos.clevertec_task1.databinding.DetailsFragmentBinding

class DetailsFragment : Fragment() {

    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = requireNotNull(_binding)
    private var sharedItem: ItemModel? = null
    private var navigation: Navigation? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigation = context as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            sharedItem = ItemModel(
                id = "null",
                image = arguments?.getString(IMAGE) ?: "",
                name = null,
                number = arguments?.getString(DESCRIPTION) ?: "",
                email = null
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fillDetails()
        setUpListeners()
    }

    private fun setUpListeners() {
        binding.toolBar.setNavigationOnClickListener {
            navigation?.navigateFirstFragment()
        }
        binding.exitBtn.setOnClickListener { navigation?.onPowerBtnPush() }
    }

    private fun fillDetails() {
        sharedItem?.let {
            with(binding) {
                detailsTitle.text = "it.name"
                detailsDescription.text = it.number
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {

        @JvmStatic
        fun newInstance(item: Bundle) =
            DetailsFragment().apply {
                arguments = item
            }
    }
}