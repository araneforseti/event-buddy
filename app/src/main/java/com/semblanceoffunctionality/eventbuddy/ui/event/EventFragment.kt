package com.semblanceoffunctionality.eventbuddy.ui.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.semblanceoffunctionality.eventbuddy.R
import com.semblanceoffunctionality.eventbuddy.databinding.FragmentEventBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventFragment : Fragment() {

    private lateinit var binding: FragmentEventBinding
    val args: EventFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val eventName = args.event
        binding = DataBindingUtil.inflate<FragmentEventBinding>(
            inflater,
            R.layout.fragment_event,
            container,
            false
        ).apply {
            viewModel = EventViewModel(eventName)
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }
}
