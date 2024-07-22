package com.example.imdb.adapters


import android.content.Context
import android.graphics.Movie
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imdb.databinding.ItemTrendingShowBinding
import com.example.imdb.models.MovieList

// adapter
class TrendingAdapter(private val list: List<MovieList>,private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        // create view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            ItemTrendingShowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }
// get size
    override fun getItemCount(): Int {
        return list.size

    }
    // bind data

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
        val binding = (holder as ViewHolder).binding


       Glide.with(context).load(item.backdrop_path).into(binding.ivTrendingImage)

    }
    // view holder

    class ViewHolder(val binding: ItemTrendingShowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}