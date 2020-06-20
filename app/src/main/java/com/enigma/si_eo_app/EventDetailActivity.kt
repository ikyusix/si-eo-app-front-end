package com.enigma.si_eo_app

import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.RequestQueue
import com.enigma.si_eo_app.model.Banner
import com.squareup.picasso.Picasso

class EventDetailActivity: AppCompatActivity() {

    lateinit var listView: ListView
    val url = "http://10.0.2.2:8586/event"
    lateinit var requestQue: RequestQueue
    lateinit var idEvent: String
    lateinit var name :String
    lateinit var date :String
    lateinit var location :String
    lateinit var prince :String
    lateinit var capacity :String
    lateinit var description :String
    lateinit var urlbanner :String
    var listBanner = mutableListOf<Banner>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_event_layout)

        idEvent = intent.getStringExtra("id")
        name = intent.getStringExtra("name")
        date = intent.getStringExtra("date")
        location = intent.getStringExtra("location")
        prince = intent.getStringExtra("prince")
        capacity = intent.getStringExtra("capacity")
        description = intent.getStringExtra("description")
        urlbanner = intent.getStringExtra("url-banner")

        var Name = findViewById<TextView>(R.id.text_namaKegiatan)
        var Date = findViewById<TextView>(R.id.text_tanggal)
        var Location = findViewById<TextView>(R.id.text_location)
        var Prince = findViewById<TextView>(R.id.text_prince)
        var Capacity = findViewById<TextView>(R.id.text_capacity)
        var Description = findViewById<TextView>(R.id.text_description)
        var ImageBanner = findViewById<ImageView>(R.id.image_scroll)


        Name.setText(name)
        Date.setText(date)
        Location.setText(location)
        Prince.setText(prince)
        Capacity.setText(capacity)
        Description.setText(description)
        Picasso.get().load(urlbanner).into(ImageBanner)
    }
}