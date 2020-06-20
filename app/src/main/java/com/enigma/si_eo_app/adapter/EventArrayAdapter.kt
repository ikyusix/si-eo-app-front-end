package com.enigma.si_eo_app.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import com.enigma.si_eo_app.R
import com.enigma.si_eo_app.model.Event
import com.squareup.picasso.Picasso

class EventArrayAdapter(@NonNull context: Context, @LayoutRes layoutRes: Int=0, var eventList: MutableList<Event>): ArrayAdapter<Event>(context, layoutRes, eventList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false) // inject ke layout // ngambil view yg mn
        val event = eventList.get(position)
        itemView?.findViewById<TextView>(R.id.eventName_text)?.setText(event.Name)
        itemView?.findViewById<TextView>(R.id.eventLocation_text)?.setText(event.Location)
        itemView?.findViewById<TextView>(R.id.eventTanggal_text)?.setText(event.Date)
        itemView?.findViewById<TextView>(R.id.capacity_text)?.setText(event.Capacity)

        val imageView = itemView?.findViewById<ImageView>(R.id.event_image_list)
        Picasso.get().load(event.UrlBanner).into(imageView)
        return itemView
    }
}