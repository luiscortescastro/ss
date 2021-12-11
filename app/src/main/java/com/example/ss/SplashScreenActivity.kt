package com.example.ss

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.firebase.auth.FirebaseAuth

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val goHome = Intent(this, MainActivity::class.java)
        val goAuth = Intent(this, AuthActivity::class.java)
        handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            if(FirebaseAuth.getInstance().currentUser != null){
                startActivity(goHome)
            }
            else {
                startActivity(goAuth)
            }
            finish()
        }, 1000)

    }
}