package com.example.imdb

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.imdb.databinding.ActivityMainBinding
import com.example.imdb.uis.SearchActivity
import com.example.imdb.uis.fragments.Home
import com.example.imdb.uis.fragments.Movies
import com.example.imdb.uis.fragments.Shows

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        with(binding) {
            setFragment(Home())

            bottomNavigationView.selectedItemId = R.id.home
            searchView.setOnClickListener {
                startActivity(Intent(this@MainActivity, SearchActivity::class.java))
            }

            bottomNavigationView.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.home -> setFragment(Home())
                    R.id.movie -> setFragment(Movies())
                    R.id.series -> setFragment(Shows())

                }
                true
            }

        }

    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout, fragment)
            commit()
        }
    }
}