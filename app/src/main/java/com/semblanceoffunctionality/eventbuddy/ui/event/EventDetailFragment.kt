package com.semblanceoffunctionality.eventbuddy.ui.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.semblanceoffunctionality.eventbuddy.R
import com.semblanceoffunctionality.eventbuddy.data.Event
import com.semblanceoffunctionality.eventbuddy.databinding.FragmentEventBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventDetailFragment : Fragment() {

    private val eventViewModel: EventDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentEventBinding>(
            inflater,
            R.layout.fragment_event,
            container,
            false
        ).apply {
            viewModel = eventViewModel
            lifecycleOwner = viewLifecycleOwner
            saveEvent.setOnClickListener {
                val eventId = eventViewModel.event.value!!.id
                val updatedEvent = Event(
                    eventName.text.toString(), eventStart.text.toString(), eventEnd.text.toString(),
                    eventRegistered.isChecked, eventPaid.isChecked, eventWebsite.text.toString(),
                    eventFacebook.text.toString(), eventScoring.text.toString(), eventVenue.text.toString(),
                    travelBooked.isChecked, eventTravelInfo.text.toString(), eventHotelBooked.isChecked,
                    eventHotelInfo.text.toString(), eventRoomies.text.toString(), eventHotelPrice.text.toString(),
                    eventId)
                eventViewModel.updateEvent(updatedEvent)
                Snackbar.make(requireView(), R.string.event_save, Toast.LENGTH_SHORT).show()
            }
            delete.setOnClickListener {
                eventViewModel.deleteSelf()
            }
        }

        return binding.root
    }
}
