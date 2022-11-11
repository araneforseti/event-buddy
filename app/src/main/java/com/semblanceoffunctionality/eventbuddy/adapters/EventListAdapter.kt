package com.semblanceoffunctionality.eventbuddy.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.semblanceoffunctionality.eventbuddy.data.Event
import com.semblanceoffunctionality.eventbuddy.databinding.ListEventCardBinding

class EventListAdapter : ListAdapter<Event, RecyclerView.ViewHolder>(EventDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return EventViewHolder(
            ListEventCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val event = getItem(position)
        (holder as EventViewHolder).bind(event)
    }

    class EventViewHolder
        (
        private val binding: ListEventCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {


        fun bind(listEvent: Event) {
            binding.apply {
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