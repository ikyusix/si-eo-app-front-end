package com.enigma.si_eo_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.enigma.si_eo_app.adapter.EventArrayAdapter
import com.enigma.si_eo_app.model.Event
import org.json.JSONArray
import org.json.JSONException

class EventActivity : AppCompatActivity() {
    lateinit var listView: ListView
    lateinit var arrayAdapter: EventArrayAdapter
    val url = "http://10.0.2.2:8586/listevent/"
    lateinit var requestQue: RequestQueue
    var listEvent = mutableListOf<Event>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_layout)
        listView = findViewById<ListView>(R.id.eventList)
        arrayAdapter = EventArrayAdapter(
            context = this,
            eventList = listEvent
        )
        requestQue =
            Volley.newRequestQueue(this) // buat request antrian utk aktivitas ini aja (this)
        listView.adapter = arrayAdapter
        fetchAll()

        listView.setOnItemClickListener { parent, view, position, id ->
            startActivity(Intent(this, EventDetailActivity::class.java).apply {
                putExtra("id", listEvent[position].Id)
                putExtra("name", listEvent[position].Name)
                putExtra("date", listEvent[position].Date)
                putExtra("location", listEvent[position].Location)
                putExtra("prince", listEvent[position].Prince)
                putExtra("capacity", listEvent[position].Capacity)
                putExtra("description", listEvent[position].Description)
                putExtra("url-banner", listEvent[position].UrlBanner)
            })
        }
    }


    fun fetchAll() { // ini fetching terjadi saat on create
        // baru ke sini
        val eventRequest = StringRequest(
            Request.Method.GET,
            url,
            Response.Listener { response ->
                resolveSuccess(response)
            },
            Response.ErrorListener { error: VolleyError? ->
                Log.e("FETCH: FAIL: ", error?.message)
            })

        requestQue.add(eventRequest) // yg dijalanin ini dulu
    }

    private fun resolveSuccess(response: String?) {
        try {
            val arrayResponse =
                JSONArray(response) // krn banyak json, jd pk json array, gbs json object
            for (i in 0 until arrayResponse.length()) {
                val item = arrayResponse.getJSONObject(i)
                val event = Event(
                    item.getString("Id"),
                    item.getString("Name"),
                    item.getString("Date"),
                    item.getString("Location"),
                    item.getString("Prince"),
                    item.getString("Capacity"),
                    item.getString("Description"),
                    item.getString("UrlBanner")
                )
                Log.i("EVENT $i", event.toString())
                arrayAdapter.add(event)
            }
        } catch (jsonEx: JSONException) {
            Log.e("PARSE: FAIL:", jsonEx.message)
        }
    }
}
