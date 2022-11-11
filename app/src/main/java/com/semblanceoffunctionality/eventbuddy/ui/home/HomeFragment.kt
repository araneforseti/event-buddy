package com.semblanceoffunctionality.eventbuddy.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.semblanceoffunctionality.eventbuddy.adapters.EventListAdapter
import com.semblanceoffunctionality.eventbuddy.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val adapter = EventListAdapter()

        binding.listEvents.adapter = adapter

        return binding.root
    }
}