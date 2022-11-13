package com.semblanceoffunctionality.eventbuddy.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.semblanceoffunctionality.eventbuddy.R
import com.semblanceoffunctionality.eventbuddy.adapters.EventListAdapter
import com.semblanceoffunctionality.eventbuddy.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: EventListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val adapter = EventListAdapter()

        binding.listEvents.adapter = adapter

        subscribeUi(adapter, binding)

        binding.addEvent.setOnClickListener {
            createItemDialog(container)
        }

        return binding.root
    }

    private fun subscribeUi(adapter: EventListAdapter, binding: FragmentHomeBinding) {
        viewModel.items.observe(viewLifecycleOwner) { result ->
            adapter.submitList(result)
        }
    }

    private fun createItemDialog(container: ViewGroup?) {
        activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val dialogView = inflater.inflate(R.layout.fragment_new_event_dialog, container, false)
            builder.apply {
                setView(dialogView)
                setPositiveButton(R.string.create) { _, _ ->
                    val eventName = dialogView.findViewById<EditText>(R.id.event_name).text.toString()
                    val eventStart = dialogView.findViewById<EditText>(R.id.event_start).text.toString()
                    val eventEnd = dialogView.findViewById<EditText>(R.id.event_end).text.toString()
                    viewModel.addItem(eventName, eventStart, eventEnd)
                }
                setNegativeButton(R.string.cancel) { dialog, _ ->
                    dialog?.cancel()
                }
            }
            builder.create()
        }?.show()
    }
}
