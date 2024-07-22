package com.example.imdb.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imdb.databinding.ParentRvModelBinding
import com.example.imdb.models.HomeApiResponseItem

// adapter
class HomeParentAdapter(private val list: List<HomeApiResponseItem>, private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    // create view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            ParentRvModelBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    // get size
    override fun getItemCount(): Int {
        return list.size - 1

    }
    // bind data

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position + 1]
        val binding = (holder as ViewHolder).binding
        binding.parentTvTitle.text = item.title
        val adapter = HomeItemAdapter(item.movies, context)
        binding.rvChild.setHasFixedSize(true)
        binding.rvChild.adapter = adapter


    }
    // view holder

    class ViewHolder(val binding: ParentRvModelBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}