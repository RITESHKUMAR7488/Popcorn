package com.example.imdb.uis

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.imdb.R
import com.example.imdb.databinding.ActivityWelcomeScreenBinding

class WelcomeScreen : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=DataBindingUtil.setContentView(this@WelcomeScreen,R.layout.activity_welcome_screen)
        with(binding){
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this@WelcomeScreen,FirstScreen::class.java))
                finish()
            },3000)

        }

    }
}