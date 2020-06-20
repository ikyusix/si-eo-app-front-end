package com.enigma.si_eo_app.EoScreen

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.FileUtils
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.enigma.si_eo_app.API.EoApi
import com.enigma.si_eo_app.R
import com.enigma.si_eo_app.config.RetrofitBuilder
import com.enigma.si_eo_app.model.Eo
import kotlinx.android.synthetic.main.activity_eo_screen.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class EoScreen : AppCompatActivity() {

    var selectImageKTP: Uri? = null
    var selectImageLicense: Uri? = null
    lateinit var imageViewKTP: ImageView
    lateinit var imageViewLicense: ImageView
    val REQUEST_IMAGE_CODE = 1
    val REQUEST_PICK_IMAGE_CODE_1 = 2
    val REQUEST_PICK_IMAGE_CODE_2 = 22
//    lateinit var eoAPI: EoApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eo_screen)
        imageViewKTP = findViewById(R.id.btnIdentityImg)
        imageViewLicense = findViewById(R.id.btnLicenseImg)
    }

    fun uploadKTP(view: View){
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CODE)
        }
    }
    fun uploadLicense(view: View){
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CODE)
        }
    }

    fun browseKTP(view: View){
        val pickPictureIntent = Intent(Intent.ACTION_PICK)
        pickPictureIntent.setType("image/*")
        val mimeTypes = arrayOf("image/jpeg", "image/png")
        pickPictureIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
        startActivityForResult(pickPictureIntent, REQUEST_PICK_IMAGE_CODE_1)
    }

    fun browseLicense(view: View){
        val pickPictureIntent = Intent(Intent.ACTION_PICK)
        pickPictureIntent.setType("image/*")
        val mimeTypes = arrayOf("image/jpeg", "image/png")
        pickPictureIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
        startActivityForResult(pickPictureIntent, REQUEST_PICK_IMAGE_CODE_2)
    }

    fun SubmitData(view: View) {

        val textEoName = findViewById<EditText>(R.id.editEoName)
        val textIdentity = findViewById<EditText>(R.id.editIdentity)
        val textLicense = findViewById<EditText>(R.id.editLicense)
        val textAddress = findViewById<EditText>(R.id.editAddress)
        val textPhone = findViewById<EditText>(R.id.editPhone)
        val textWebsite = findViewById<EditText>(R.id.editWebsite)
        val textInstagram = findViewById<EditText>(R.id.editInstagram)
        val textFacebook = findViewById<EditText>(R.id.editFacebook)
        val textTwitter = findViewById<EditText>(R.id.editTwitter)

        val eoName = textEoName.text.toString().trim { it <= ' ' }
        val identity = textIdentity.text.toString().trim { it <= ' ' }
        val license = textLicense.text.toString().trim { it <= ' ' }
        val address = textAddress.text.toString().trim { it <= ' ' }
        val phone = textPhone.text.toString().trim { it <= ' ' }
        val website = textWebsite.text.toString().trim { it <= ' ' }
        val instagram = textInstagram.text.toString().trim { it <= ' ' }
        val facebook = textFacebook.text.toString().trim { it <= ' ' }
        val twitter = textTwitter.text.toString().trim { it <= ' ' }


        if (eoName.isEmpty()) {
            textEoName.setError("Event Organizer Name Is Required")
            textEoName.requestFocus()
            return
        }

        if (identity.isEmpty()) {
            textIdentity.setError("Identity Number Is Required")
            textIdentity.requestFocus()
            return
        }

        if (license.isEmpty()) {
            textLicense.setError("License Is Required")
            textLicense.requestFocus()
            return
        }

        if (address.isEmpty()) {
            textAddress.setError("Address Number Is Required")
            textAddress.requestFocus()
            return
        }

        if (phone.isEmpty()) {
            textPhone.setError("Phone Number Is Required")
            textPhone.requestFocus()
            return
        }

        if (website.isEmpty()) {
            textWebsite.setError("Website Is Required")
            textWebsite.requestFocus()
            return
        }

        if (instagram.isEmpty()) {
            textInstagram.setError("Instagram Is Required")
            textInstagram.requestFocus()
            return
        }

        if (facebook.isEmpty()) {
            textFacebook.setError("Facebook Is Required")
            textFacebook.requestFocus()
            return
        }

        if (twitter.isEmpty()) {
            textTwitter.setError("Twitter Is Required")
            textTwitter.requestFocus()
            return
        }

        if (selectImageKTP == null){
            textIdentity.setError("Identity Image Is Required")
            textIdentity.requestFocus()
            return
        }
        if (selectImageLicense == null){
            textIdentity.setError("License Image Is Required")
            textIdentity.requestFocus()
            return
        }
        val pathOriginalKTP = FileUtil.Companion.getRealPathFromURI(selectImageKTP!!, this)
        val file = File(pathOriginalKTP)
        val multiPartBodyKTP = MultipartBody.Part.createFormData(
            "IdentityImg",
            file.name,
            file.asRequestBody("image/*".toMediaType())
        )
        val pathOriginalLicense = FileUtil.Companion.getRealPathFromURI(selectImageLicense!!, this)
        val fileLicense = File(pathOriginalLicense)
        val multiPartBodyLicense = MultipartBody.Part.createFormData(
            "LicenseImg",
            fileLicense.name,
            fileLicense.asRequestBody("image/*".toMediaType())
        )

        RetrofitBuilder.instance.saveEo(1, eoName, identity, multiPartBodyKTP, license, multiPartBodyLicense, address, phone, website, instagram, facebook, twitter)!!
            .enqueue(object : Callback<Eo> {
                override fun onFailure(call: Call<Eo>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<Eo>, response: Response<Eo>) {
                    Toast.makeText(this@EoScreen, "Succses Send Data", Toast.LENGTH_LONG).show()
                    finish()
                }
            })
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_PICK_IMAGE_CODE_1 && resultCode == Activity.RESULT_OK) {
            selectImageKTP = data?.data
            imageViewKTP.setImageURI(selectImageKTP)
        }
        if (requestCode == REQUEST_PICK_IMAGE_CODE_2 && resultCode == Activity.RESULT_OK) {
            selectImageLicense = data?.data
            imageViewLicense.setImageURI(selectImageLicense)
        }
    }

}


