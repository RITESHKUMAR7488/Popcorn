package com.example.imdb.adapters


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imdb.databinding.ChildRvModelBinding
import com.example.imdb.models.MovieList
import com.example.imdb.models.MoviesAPiResponse
import com.example.imdb.uis.MoviesDescription

// adapter
class HomeItemAdapter(
    private val list: List<MovieList>,
    private val context: Context,
    private val isShows: Boolean? = false
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    // create view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            ChildRvModelBinding.inflate(
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


        Glide.with(context).load(item.poster_path).into(binding.homeChildImage)
        binding.homeChildImage.setOnClickListener {
            val intent = Intent(context, MoviesDescription::class.java)

            val isShows = item.contentType == "show"

            // path for next activity
            intent.putExtra("isShow", isShows)
            intent.putExtra("ids", item._id)
            context.startActivity(intent)

        }

    }
    // view holder

    class ViewHolder(val binding: ChildRvModelBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}