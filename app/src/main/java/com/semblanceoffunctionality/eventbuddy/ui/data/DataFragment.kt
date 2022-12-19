package com.semblanceoffunctionality.eventbuddy.ui.data

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast.LENGTH_SHORT
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.semblanceoffunctionality.eventbuddy.R
import com.semblanceoffunctionality.eventbuddy.data.Event
import com.semblanceoffunctionality.eventbuddy.databinding.FragmentDataBinding
import com.semblanceoffunctionality.eventbuddy.databinding.FragmentEventBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DataFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentDataBinding>(
            inflater,
            R.layout.fragment_data,
            container,
            false
        ).apply {
            lifecycleOwner = viewLifecycleOwner
            importData.setOnClickListener {
                Snackbar.make(requireView(), R.string.import_data, LENGTH_SHORT).show()
            }
            exportData.setOnClickListener {
                Snackbar.make(requireView(), R.string.export_data, LENGTH_SHORT).show()
            }
        }

        return binding.root
    }
}
