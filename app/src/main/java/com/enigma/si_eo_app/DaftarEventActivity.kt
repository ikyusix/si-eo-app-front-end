package com.enigma.si_eo_app

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.enigma.si_eo_app.model.EventInput
import com.enigma.si_eo_app.service.FileUtil
import com.enigma.si_eo_app.service.RetrofitBuilder
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class DaftarEventActivity : AppCompatActivity() {

    val REQUEST_IMAGE_CODE = 1
    val REQUEST_PICK_IMAGE_CODE = 11
    lateinit var imageView: ImageView
    var selectImage: Uri? = null

    var nama: String = ""
    var location: String = ""
    var date: String = ""
    var prince: String = ""
    var capacity: String = ""
    var description: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.daftar_event_layout)
        imageView = findViewById(R.id.imageView)

    }

    fun dispatchTakePictureIntent(view: View) {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CODE)
        }
    }

    fun browseImageFromGallery(view: View) {
        val pickPictureIntent = Intent(Intent.ACTION_PICK)
        pickPictureIntent.setType("image/*")
        val mimeTypes = arrayOf("image/jpeg", "image/png")
        pickPictureIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
        startActivityForResult(pickPictureIntent, REQUEST_PICK_IMAGE_CODE)
    }

    fun uploadData(view: View) {
        nama = findViewById<EditText>(R.id.name_text).text.toString()
        location = findViewById<EditText>(R.id.location_text).text.toString()
        date = findViewById<EditText>(R.id.date_text).text.toString()
        prince = findViewById<EditText>(R.id.prince_text).text.toString()
        capacity = findViewById<EditText>(R.id.capacity_text).text.toString()
        description = findViewById<EditText>(R.id.description_text).text.toString()

        val pathOriginal = FileUtil.Companion?.getRealPathFromURI(selectImage!!, this)
        val file = File(pathOriginal)
        val multiPartBody = MultipartBody.Part.createFormData(
            "image",
            file.name,
            file.asRequestBody("image/*".toMediaType())
        )

        RetrofitBuilder.instance.SaveEvent(1, nama, date, location, prince, capacity, description,multiPartBody).enqueue(object : Callback<EventInput> {
                override fun onFailure(call: Call<EventInput>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<EventInput>, response: Response<EventInput>) {
                    Toast.makeText(applicationContext, "Succses Send Data", Toast.LENGTH_LONG)
                        .show()
                    finish()
                }
            })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_PICK_IMAGE_CODE && resultCode == Activity.RESULT_OK) {
            selectImage = data?.data
            imageView.setImageURI(selectImage)
        }
    }

//    fun InsertEvent(view: View) {
//        var nama = findViewById<EditText>(R.id.name_text).text.toString()
//        var location = findViewById<EditText>(R.id.location_text).text.toString()
//        var date = findViewById<EditText>(R.id.date_text).text.toString()
//        var prince = findViewById<EditText>(R.id.prince_text).text.toString()
//        var capacity = findViewById<EditText>(R.id.capacity_text).text.toString()
//        var description = findViewById<EditText>(R.id.description_text).text.toString()
//        val newIntent = Intent(this, EventDetailActivity::class.java).apply {
//            putExtra("name", nama)
//            putExtra("location", location)
//            putExtra("date", date)
//            putExtra("prince", prince)
//            putExtra("capacity", capacity)
//            putExtra("description", description)
//        }
//        startActivity(newIntent)
//    }

}