package com.example.imdb.uis

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.imdb.MainActivity
import com.example.imdb.R
import com.example.imdb.databinding.ActivityFirstScreenBinding

class FirstScreen : AppCompatActivity() {
    private lateinit var binding: ActivityFirstScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // checking if user is already logged in
        val sharedpref=getSharedPreferences("imdb", Context.MODE_PRIVATE)
        val loggedIn=sharedpref.getBoolean("loggedIn",false)

        if(loggedIn){
            val intent=Intent(this@FirstScreen, MainActivity::class.java)
            startActivity(intent)
            finish()
            return

        }

        binding = DataBindingUtil.setContentView(this@FirstScreen, R.layout.activity_first_screen)
        with(binding) {
            btnLogin.setOnClickListener {
                val intent = Intent(this@FirstScreen, LoginScreen::class.java)
                startActivity(intent)

            }
            btnSignUp.setOnClickListener {
                val intent = Intent(this@FirstScreen, RegisterScreen::class.java)
                startActivity(intent)
                
            }

        }
    }
}