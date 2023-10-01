package edu.mirea.onebeattrue.hangoutapp.presentation.eventlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.mirea.onebeattrue.hangoutapp.databinding.ItemEventBinding
import edu.mirea.onebeattrue.hangoutapp.domain.eventlist.entities.EventItem

class EventListAdapter : ListAdapter<EventItem, EventItemViewHolder>(EventItemDiffCallback()) {

    lateinit var onEventItemClickListener: ((EventItem) -> Unit)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventItemViewHolder {
        val binding = ItemEventBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EventItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventItemViewHolder, position: Int) {
        val eventItem = getItem(position)
        val binding = holder.binding

        binding.root.setOnClickListener {
            onEventItemClickListener(eventItem)
        }

        binding.eventItem = eventItem
    }
}

class EventItemViewHolder(
    val binding: ItemEventBinding
) : RecyclerView.ViewHolder(
    binding.root
)

class EventItemDiffCallback : DiffUtil.ItemCallback<EventItem>() {
    override fun areItemsTheSame(oldItem: EventItem, newItem: EventItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: EventItem, newItem: EventItem): Boolean {
        return oldItem == newItem
    }

}