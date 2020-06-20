package com.enigma.si_eo_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.enigma.si_eo_app.config.RetrofitBuilder
import com.enigma.si_eo_app.model.ResponseSignIn
import com.enigma.si_eo_app.model.SignIn
import com.enigma.si_eo_app.service.ServiceAPI
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)

    }

    fun SignInBtn (view: View) {
        val email = findViewById<TextInputEditText>(R.id.edit_email).text.toString()
        val password = findViewById<TextInputEditText>(R.id.password_edit_text).text.toString()
        val serviceAPI = RetrofitBuilder.createRetrofit().create(ServiceAPI::class.java)
        Log.i("EMAIL", email)
        Log.i("PASSWORD", password)
        serviceAPI.signInPost(SignIn(email, password)).enqueue(object: Callback<ResponseSignIn> {
            override fun onFailure(call: Call<ResponseSignIn>, t: Throwable) {
                Log.i("SIGN IN", "Message ${t.message}")
            }

            override fun onResponse( call: Call<ResponseSignIn>, response: Response<ResponseSignIn>) {
                Log.i("MASUK","TES")
                var newVar = response.body()
                var success = newVar?.Success
                var data = newVar?.data
                var message = newVar?.successMessage

                if(response.isSuccessful){
                    var responseJSON = response?.errorBody()
                    println("response login"+responseJSON.toString())
                    println("message"+success)
                    println("data message :"+data?.Email.toString())

                    if (data == null){
                        Toast.makeText(this@LoginActivity, "Login filed response", Toast.LENGTH_SHORT).show()
                    }else {
                        Toast.makeText(this@LoginActivity, "Login success", Toast.LENGTH_SHORT).show()
                        changeActivity(email)
                    }
                    return
                } else {
                    Toast.makeText(this@LoginActivity, "Login filed", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun clickRegister (view: View){
        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
    }

    fun changeActivity(email: String) {
        val intent = Intent(this, HomeScreen::class.java).apply {
            putExtra("email", email)
            finish()
        }
        startActivity(intent)
    }
}