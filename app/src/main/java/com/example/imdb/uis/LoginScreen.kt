package com.example.imdb.uis

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.imdb.MainActivity
import com.example.imdb.R
import com.example.imdb.databinding.ActivityLoginScreenBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginScreen : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this@LoginScreen, R.layout.activity_login_screen)
        with(binding) {
            auth=FirebaseAuth.getInstance()
            btnLogin.setOnClickListener {
                val userEmail = etEmail.text.toString().trim()
                val userPassword = etPassword.text.toString().trim()

                if (userEmail.isBlank() || userPassword.isBlank()) {
                    Toast.makeText(
                        this@LoginScreen,
                        "please fill all the blanks",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    auth.signInWithEmailAndPassword(userEmail, userPassword)
                        .addOnCompleteListener(this@LoginScreen) { task ->
                            if (task.isSuccessful) {
                                Log.d("isSuccessful", "true")
                                Toast.makeText(
                                    this@LoginScreen,
                                    "Login is successful",
                                    Toast.LENGTH_SHORT
                                ).show()

                                // save log in state
                                val sharedpref= getSharedPreferences("imdb", MODE_PRIVATE)
                                val editor=sharedpref.edit()
                                editor.putBoolean("loggedIn",true)
                                editor.apply()


                                startActivity(Intent(this@LoginScreen, MainActivity::class.java))
                                finish()

                            } else {
                                Log.d("isSuccessful", "false")
                                Toast.makeText(
                                    this@LoginScreen,
                                    "Login failed :${task.exception?.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }


                        }

                }

            }
            txtRegister.setOnClickListener{
                val intent=Intent(this@LoginScreen, RegisterScreen::class.java)
                startActivity(intent)
                finish()
            }

        }

    }

    override fun onStart() {
        super.onStart()

        //check if user is already logged in
        val currentUser: FirebaseUser? = auth.currentUser

        if (currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}