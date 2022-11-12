package com.semblanceoffunctionality.eventbuddy.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.semblanceoffunctionality.eventbuddy.R
import com.semblanceoffunctionality.eventbuddy.data.Event
import com.semblanceoffunctionality.eventbuddy.databinding.ListEventCardBinding
import com.semblanceoffunctionality.eventbuddy.ui.home.EventViewModel

class EventListAdapter() :
    ListAdapter<Event, EventListAdapter.ViewHolder>(
        EventDiffCallback()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_event_card,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = getItem(position)
        holder.bind(event)
    }

    class ViewHolder
        (
        private val binding: ListEventCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listEvent: Event) {
            binding.apply {
                viewModel = EventViewModel(listEvent)
                eventName.text = listEvent.name
            }
        }
    }
}

private class EventDiffCallback : DiffUtil.ItemCallback<Event>() {

    override fun areItemsTheSame(oldEvent: Event, newEvent: Event): Boolean {
        return oldEvent.name == newEvent.name
    }

    override fun areContentsTheSame(oldEvent: Event, newEvent: Event): Boolean {
        return oldEvent == newEvent
    }
}