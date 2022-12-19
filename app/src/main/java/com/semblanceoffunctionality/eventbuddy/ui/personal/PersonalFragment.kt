package com.semblanceoffunctionality.eventbuddy.ui.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.semblanceoffunctionality.eventbuddy.R
import com.semblanceoffunctionality.eventbuddy.data.Personal
import com.semblanceoffunctionality.eventbuddy.databinding.FragmentPersonalBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonalFragment : Fragment() {
    private val personalViewModel: PersonalViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentPersonalBinding>(
            inflater,
            R.layout.fragment_personal,
            container,
            false
        ).apply {
            viewModel = personalViewModel
            lifecycleOwner = viewLifecycleOwner
            saveInfo.setOnClickListener {
                val info = personalViewModel.info
                val infoToUpdate : Personal = info.value!!
                infoToUpdate.number = myWsdcNumber.text.toString()
                personalViewModel.updateInfo(info.value!!)
            }
        }

        return binding.root
    }
}