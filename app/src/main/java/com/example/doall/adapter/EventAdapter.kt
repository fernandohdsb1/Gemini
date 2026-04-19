package com.example.doall.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.doall.databinding.ItemEventBinding
import com.example.doall.model.Event

// El Adapter recibe la lista de eventos por el constructor
class EventAdapter(private var eventList: List<Event>) :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    // El ViewHolder es la "caja" que guarda las referencias a los TextViews y botones de item_event.xml
    inner class EventViewHolder(val binding: ItemEventBinding) :
        RecyclerView.ViewHolder(binding.root)

    // 1. Aquí el Adapter infla (crea) el diseño XML por cada elemento
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemEventBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return EventViewHolder(binding)
    }

    // 2. Aquí el Adapter coge los datos y los "pega" en el XML
    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = eventList[position]

        with(holder.binding) {
            tvEventName.text = event.name
            tvEventSubtitle.text = event.location
            tvFriends.text = "${event.friendsAttending} amigos"
        }
    }

    // 3. Le dice al RecyclerView cuántos elementos hay en total
    override fun getItemCount(): Int = eventList.size

    // Función extra que creamos para actualizar la lista cuando lleguen datos nuevos de Firebase
    fun updateData(newList: List<Event>) {
        eventList = newList
        notifyDataSetChanged() // Avisa al RecyclerView de que repinte la lista
    }
}