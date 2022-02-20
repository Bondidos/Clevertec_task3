package com.bondidos.clevertec_task1.presentation.ui.fragments.startScreen

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bondidos.clevertec_task1.R
import com.bondidos.clevertec_task1.databinding.StartScreenFragmentBinding
import com.bondidos.clevertec_task1.domain.constants.Const
import com.bondidos.clevertec_task1.domain.model.ItemModel
import com.bondidos.clevertec_task1.domain.state.IsSuccess
import com.bondidos.clevertec_task1.presentation.ui.navigation.Navigation
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class StartScreen : Fragment() {
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
        with(binding) {
            showContacts.setOnClickListener { navigation?.navigateFirstFragment() }
            selectContact.setOnClickListener {
                lifecycleScope.launchWhenCreated {
                    viewModel.numberList.collect { state ->
                        when (state) {
                            is IsSuccess.SuccessWithData -> {
                                // i know, this is no good practice to map data in UI, but i have no time ;)
                                val prepareList =
                                    state.data.filter { it.number != null && it.number.isNotEmpty() }
                                        .map { it.number }
                                if (prepareList.isNotEmpty()) {
                                    navigation?.openListDialog(prepareList)
                                }
                                else {
                                    Snackbar.make(
                                        binding.coordinatorLayout,
                                        "No saved Numbers in DataBase",
                                        BaseTransientBottomBar.LENGTH_LONG
                                    ).show()
                                }
                            }
                            is IsSuccess.Error -> Snackbar.make(
                                binding.coordinatorLayout,
                                state.message,
                                BaseTransientBottomBar.LENGTH_LONG
                            ).show()
                            else -> Unit
                        }
                    }
                }
            }
            showFromSP.setOnClickListener {
                val number = getNumberFromSP()
                if (number != "") {
                    Snackbar.make(
                        binding.coordinatorLayout,
                        "Saved in SP: $number",
                        BaseTransientBottomBar.LENGTH_LONG
                    ).show()
                } else {
                    Snackbar.make(
                        binding.coordinatorLayout,
                        "No saved number in SP",
                        BaseTransientBottomBar.LENGTH_LONG
                    ).show()
                }
            }
            showNotification.setOnClickListener {
                val number = getNumberFromSP()
                if (number != "")
                    viewModel.findContact(getNumberFromSP())
                else Snackbar.make(
                    binding.coordinatorLayout,
                    "No saved number in SP",
                    BaseTransientBottomBar.LENGTH_LONG
                ).show()
            }
            lifecycleScope.launchWhenCreated {
                viewModel.contact.collect {
                    when (it) {
                        is IsSuccess.SuccessWithData -> createNotification(it.data)
                        is IsSuccess.Error -> Snackbar.make(
                            binding.coordinatorLayout,
                            it.message,
                            BaseTransientBottomBar.LENGTH_LONG
                        ).show()
                        else -> Unit
                    }
                }
            }
        }
    }

    private fun getNumberFromSP(): String {
        val sp = requireActivity().getSharedPreferences(Const.SHARED_PREFS, Context.MODE_PRIVATE)
        return sp.getString(Const.PHONE_NUMBER, "") ?: ""
    }

    private fun createNotification(contact: List<ItemModel>) {

        val builder = NotificationCompat.Builder(requireContext(), Const.CHANNEL_ID)
            .setSmallIcon(R.drawable.clevertec)
            .setContentTitle(contact.first().name?.get(Const.GIVEN_NAME) ?: "No given name")
            .setContentText("Number: " + contact.first().number + contact.first().email?.let { " Email: $it" })
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelName = Const.CHANNEL_NAME
            val descriptionText = Const.DESCRIPTION
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(Const.CHANNEL_ID, channelName, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                context?.applicationContext?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
            notificationManager.notify(Const.NOTIFICATION_ID, builder.build())
        } else {

            val notificationManager: NotificationManagerCompat =
                NotificationManagerCompat.from(requireActivity().applicationContext)
            notificationManager.notify(Const.NOTIFICATION_ID, builder.build())
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}