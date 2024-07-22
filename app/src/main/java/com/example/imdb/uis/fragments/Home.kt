package com.example.imdb.uis.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.imdb.MyApplication
import com.example.imdb.R
import com.example.imdb.adapters.HomeParentAdapter
import com.example.imdb.adapters.TrendingAdapter
import com.example.imdb.apiInterface.Api
import com.example.imdb.databinding.FragmentHomeBinding
import com.example.imdb.repository.Repository
import com.example.imdb.viewModelFactory.MyViewModelFactory
import com.example.imdb.viewModels.MyViewModel

class Home : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: MyViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            // ye code retrofit call karne ke liye hai

            val application = requireActivity().application as MyApplication
            val retrofitBuilder = application.retrofit
            val apiInterface = retrofitBuilder.create(Api::class.java)
            val repository = Repository(apiInterface)
            viewModel = ViewModelProvider(
                requireActivity(),
                MyViewModelFactory(repository)
            )[MyViewModel::class.java]

            viewModel.getHome().observe(viewLifecycleOwner) {
                binding.shimmerLayout.stopShimmer()
                binding.shimmerLayout.visibility=View.GONE
                binding.nestedScrollView.visibility=View.VISIBLE
                Log.d("dataaa", "${it[0].movies[0]}")
                val adapter = TrendingAdapter(it[0].movies, requireContext())
                trendingRv.setHasFixedSize(true)
                val snapHelper = PagerSnapHelper()
                snapHelper.attachToRecyclerView(trendingRv)


                trendingRv.adapter = adapter


                val pAdapter=HomeParentAdapter(it,requireActivity())
                rvParent.setHasFixedSize(true)
                rvParent.adapter=pAdapter
            }

        }
    }


}