package com.semblanceoffunctionality.eventbuddy.ui.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SwitchCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.semblanceoffunctionality.eventbuddy.R
import com.semblanceoffunctionality.eventbuddy.data.Event
import com.semblanceoffunctionality.eventbuddy.databinding.FragmentEventBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventFragment : Fragment() {

    private lateinit var binding: FragmentEventBinding
    private val viewModel: EventViewModel by viewModels()
    private val args: EventFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val eventName = args.event
        viewModel.updateEventName(eventName)

        binding = DataBindingUtil.inflate<FragmentEventBinding>(
            inflater,
            R.layout.fragment_event,
            container,
            false
        ).apply {
            lifecycleOwner = viewLifecycleOwner
        }

        binding.editEvent.setOnClickListener {
            editEventDialog(container)
        }

        return binding.root
    }

    private fun editEventDialog(container: ViewGroup?) {
        activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val dialogView = inflater.inflate(R.layout.fragment_edit_event_dialog, container, false)
            builder.apply {
                setView(dialogView)
                setPositiveButton(R.string.save) { _, _ ->
                    val eventName = dialogView.findViewById<EditText>(R.id.event_name).text.toString()
                    val eventStart = dialogView.findViewById<EditText>(R.id.event_start).text.toString()
                    val eventEnd = dialogView.findViewById<EditText>(R.id.event_end).text.toString()
                    val registered = dialogView.findViewById<SwitchCompat>(R.id.event_registered).isChecked
                    val eventWebsite = dialogView.findViewById<EditText>(R.id.event_website).text.toString()
                    val eventFacebook = dialogView.findViewById<EditText>(R.id.event_facebook).text.toString()
                    val eventScoring = ""//TODO: dialogView.findViewById<EditText>(R.id.event_scoring).text.toString()
                    val eventVenue = dialogView.findViewById<EditText>(R.id.event_venue).text.toString()
                    val travelBooked = dialogView.findViewById<SwitchCompat>(R.id.travel_booked).isChecked
                    val travelInfo = dialogView.findViewById<EditText>(R.id.travel_info).text.toString()
                    val hotelBooked = dialogView.findViewById<SwitchCompat>(R.id.hotel_booked).isChecked
                    val hotelInfo = dialogView.findViewById<EditText>(R.id.hotel_info).text.toString()
                    val roomies = dialogView.findViewById<EditText>(R.id.roomies).text.toString()
                    val expectedPrice = dialogView.findViewById<EditText>(R.id.expected_price).text.toString()
                    val newEvent = Event(
                        viewModel.event.value!!.id,
                        eventName,
                        eventStart,
                        eventEnd,
                        registered,
                        eventWebsite,
                        eventFacebook,
                        eventScoring,
                        eventVenue,
                        travelBooked,
                        travelInfo,
                        hotelBooked,
                        hotelInfo,
                        roomies,
                        expectedPrice
                    )
                    viewModel.updateEvent(newEvent)
                }
                setNegativeButton(R.string.cancel) { dialog, _ ->
                    dialog?.cancel()
                }
            }
            builder.create()
        }?.show()
    }
}
