package com.enigma.si_eo_app.SplashScreen

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.enigma.si_eo_app.EoScreen.EoScreen
import com.enigma.si_eo_app.MainActivity
import com.enigma.si_eo_app.R

class SplashScreen : AppCompatActivity() {
    lateinit var handler: Handler
    lateinit var image:ImageView
    lateinit var fromtop:Animation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        image = findViewById<ImageView>(R.id.sieoLogo)
        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop)
        image.setAnimation(fromtop)
            handler = Handler()
            handler.postDelayed({

                // Delay and Start Activity
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            } , 2700) // here we're delaying to startActivity after 3seconds

        }
    }
