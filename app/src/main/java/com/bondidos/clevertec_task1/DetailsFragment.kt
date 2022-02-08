package com.bondidos.clevertec_task1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bondidos.clevertec_task1.Const.DESCRIPTION
import com.bondidos.clevertec_task1.Const.IMAGE
import com.bondidos.clevertec_task1.Const.TITLE
import com.bondidos.clevertec_task1.databinding.DetailsFragmentBinding

class DetailsFragment : Fragment() {

    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = requireNotNull(_binding)
    private var sharedItem: ItemModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            sharedItem = ItemModel(
                image = arguments?.getString(IMAGE) ?: "",
                title = arguments?.getString(TITLE) ?: "",
                description = arguments?.getString(DESCRIPTION) ?: ""
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedItem?.let {
            with(binding){
                detailsTitle.text = it.title
                detailsDescription.text = it.description
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