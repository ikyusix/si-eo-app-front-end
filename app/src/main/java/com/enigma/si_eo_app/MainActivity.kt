package com.enigma.si_eo_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.enigma.si_eo_app.EoScreen.EoScreen
import androidx.fragment.app.Fragment
import com.enigma.si_eo_app.UserScreen.ProfileActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

    }

    fun ProfileScreen(view: View) {
        val intent = Intent(this,ProfileActivity::class.java)
        startActivity(intent)
    }
    fun EventList(view: View) {
        val intent = Intent(this,EventActivity::class.java)
        startActivity(intent)
    }
    fun UpgradeEO(view: View) {
        val intent = Intent(this,EoScreen::class.java)
        startActivity(intent)
    }
    fun EventScreen(view: View) {
        val intent = Intent(this,DaftarEventActivity::class.java)
        startActivity(intent)
    }
    fun SignOut(view: View) {
        finish()
    }
}
