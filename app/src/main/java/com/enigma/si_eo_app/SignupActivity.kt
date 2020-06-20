package com.enigma.si_eo_app

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.enigma.si_eo_app.config.RetrofitBuilder
import com.enigma.si_eo_app.model.ResponseSignUP
import com.enigma.si_eo_app.model.SignUp
import com.enigma.si_eo_app.service.ServiceAPI
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_layout)
    }

    fun SignUpBtn (view: View){
        var Fullname = findViewById<TextInputEditText>(R.id.fullName).text.toString()
        var Email = findViewById<TextInputEditText>(R.id.email).text.toString()
        var Username = findViewById<TextInputEditText>(R.id.userName).text.toString()
        var Password = findViewById<TextInputEditText>(R.id.password).text.toString()
        val serviceAPI = RetrofitBuilder.createRetrofit().create(ServiceAPI::class.java)
        serviceAPI.signUpPost(SignUp(Fullname, Email, Username, Password)).enqueue(object:
            Callback<ResponseSignUP> {
            override fun onFailure(call: Call<ResponseSignUP>, t: Throwable) {
                Log.e("Tag", "Error ${t.message}")
            }

            override fun onResponse(call: Call<ResponseSignUP>, response: Response<ResponseSignUP>) {
                var newVar = response.body()
                var data = newVar?.response
                if (data != null){
                    Toast.makeText(this@SignupActivity, "Registration successful", Toast.LENGTH_SHORT).show()
                    onBackPressed()
                }else {
                    Toast.makeText(this@SignupActivity, "Registration failed", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}