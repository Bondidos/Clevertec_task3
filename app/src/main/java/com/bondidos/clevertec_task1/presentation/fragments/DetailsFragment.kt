package com.bondidos.clevertec_task1.presentation.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.bondidos.clevertec_task1.domain.constants.Const.IMAGE
import com.bondidos.clevertec_task1.domain.model.ItemModel
import com.bondidos.clevertec_task1.presentation.MainActivity
import com.bondidos.clevertec_task1.presentation.navigation.Navigation
import com.bondidos.clevertec_task1.databinding.DetailsFragmentBinding
import com.bondidos.clevertec_task1.domain.constants.Const.DISPLAY_NAME
import com.bondidos.clevertec_task1.domain.constants.Const.EMAIL
import com.bondidos.clevertec_task1.domain.constants.Const.FAMILY_NAME
import com.bondidos.clevertec_task1.domain.constants.Const.GIVEN_NAME
import com.bondidos.clevertec_task1.domain.constants.Const.ID
import com.bondidos.clevertec_task1.domain.constants.Const.PHONE_NUMBER
import com.bondidos.clevertec_task1.domain.state.IsSuccess
import com.bondidos.clevertec_task1.presentation.fragments.viewModel.DetailFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = requireNotNull(_binding)
    private var sharedItem: ItemModel? = null
    private var navigation: Navigation? = null

    @Inject
    lateinit var viewModel: DetailFragmentViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigation = context as Navigation
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { args ->

            val names = mapOf(
                GIVEN_NAME to args.getString(GIVEN_NAME),
                FAMILY_NAME to args.getString(FAMILY_NAME),
                DISPLAY_NAME to args.getString(DISPLAY_NAME),
            )

            sharedItem = ItemModel(
                id = args.getString(ID) ?: "",
                image = args.getString(IMAGE) ?: "",
                name = names,
                number = args.getString(PHONE_NUMBER) ?: "",
                email = args.getString(EMAIL) ?: ""
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
        lifecycleScope.launchWhenCreated {
            viewModel.isAdded.collect{ isSuccess ->
                when(isSuccess){
                    is IsSuccess.Success ->
                        Toast.makeText(requireContext(), "Item added", Toast.LENGTH_LONG).show()
                    is IsSuccess.Error ->
                        Toast.makeText(requireContext(), isSuccess.message, Toast.LENGTH_LONG).show()
                    else -> Unit
                }
            }
        }
        binding.toolBar.setNavigationOnClickListener {
            navigation?.navigateHome()
        }
        binding.exitBtn.setOnClickListener { navigation?.onPowerBtnPush() }
        binding.saveContact.setOnClickListener {
            sharedItem?.let {
                viewModel.saveContact(it)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun fillDetails() {
        sharedItem?.let {
            with(binding) {
                detailsImage.setImageURI(Uri.parse(it.image))
                firstName.text = "Name: " + it.name?.get(GIVEN_NAME)
                familyName.text = "Family name: " + it.name?.get(FAMILY_NAME)
                phoneNumber.text = "phone: " + it.number
                email.text = "Email: " + it.email
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {

        @JvmStatic
        fun newInstance(item: ItemModel) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ID, item.id)
                    putString(IMAGE, item.image)
                    putString(GIVEN_NAME, item.name?.get(GIVEN_NAME))
                    putString(FAMILY_NAME, item.name?.get(FAMILY_NAME))
                    putString(DISPLAY_NAME, item.name?.get(DISPLAY_NAME))
                    putString(PHONE_NUMBER, item.number)
                    putString(EMAIL, item.email)
                }
            }
    }
}