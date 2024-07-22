package com.example.imdb.uis.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.imdb.R
import com.example.imdb.adapters.HomeItemAdapter
import com.example.imdb.databinding.FragmentSimmilarBinding
import com.example.imdb.models.MovieList


class Simmilar(val movie: List<MovieList>) : Fragment() {
    private lateinit var binding: FragmentSimmilarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= DataBindingUtil.inflate(layoutInflater,R.layout.fragment_simmilar,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            val adapter=HomeItemAdapter(movie,requireActivity())
            rvChild.adapter=adapter
        }
    }


}