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
import com.example.imdb.databinding.ActivityRegisterScreenBinding
import com.example.imdb.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterScreen : AppCompatActivity() {
    private var db = Firebase.firestore
    private lateinit var auth: FirebaseAuth

    private lateinit var binding: ActivityRegisterScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding =
            DataBindingUtil.setContentView(this@RegisterScreen, R.layout.activity_register_screen)
        with(binding) {
            auth = FirebaseAuth.getInstance()
            btnRegister.setOnClickListener {

                val email = binding.etEmail.text.toString()
                val username = binding.etUserName.text.toString()
                val password = binding.etPassword.text.toString()
                val repeatPassword = binding.etConfPassword.text.toString()

                //check if any field is blank
                if (email.isBlank() || username.isBlank() || password.isBlank() || repeatPassword.isBlank()) {
                    Toast.makeText(
                        this@RegisterScreen,
                        "please fill all the blanks",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } else if (password != repeatPassword) {
                    Toast.makeText(
                        this@RegisterScreen,
                        "password is not matching",
                        Toast.LENGTH_SHORT
                    )
                        .show()


                } else {
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this@RegisterScreen) { task ->

                            if (task.isSuccessful) {
                                Toast.makeText(
                                    this@RegisterScreen,
                                    "Registration is succesfull",
                                    Toast.LENGTH_SHORT
                                ).show()

                                // save register state
                                val sharedpref=getSharedPreferences("imdb", MODE_PRIVATE)
                                val editor=sharedpref.edit()
                                editor.putBoolean("loggedIn",true)
                                editor.apply()


                                // it is for storing data in firebase database
                                val user = User(username, email, password)


                                val db = Firebase.firestore


                                db.collection("users")
                                    .add(user)
                                    .addOnSuccessListener { documentReference ->

                                        startActivity(
                                            Intent(
                                                this@RegisterScreen,
                                                MainActivity::class.java
                                            )
                                        )
                                        finish()
                                        Log.d(
                                            "response",
                                            "DocumentSnapshot added with ID: ${documentReference.id}"
                                        )
                                    }
                                    .addOnFailureListener { e ->
                                        Log.w("response", "Error adding document", e)
                                    }


                            } else {
                                Toast.makeText(
                                    this@RegisterScreen,
                                    "Registration failed :${task.exception?.message}",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }


                        }

                }


            }
            txtLogin.setOnClickListener{
                val intent=Intent(this@RegisterScreen,LoginScreen::class.java)
                startActivity(intent)
                finish()
            }
        }

    }
}