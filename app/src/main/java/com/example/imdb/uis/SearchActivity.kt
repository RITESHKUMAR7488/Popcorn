package com.example.imdb.uis

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.imdb.MyApplication
import com.example.imdb.R
import com.example.imdb.adapters.HomeItemAdapter
import com.example.imdb.apiInterface.Api
import com.example.imdb.databinding.ActivitySearchBinding
import com.example.imdb.repository.Repository
import com.example.imdb.viewModelFactory.MyViewModelFactory
import com.example.imdb.viewModels.MyViewModel

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var viewModel: MyViewModel
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= DataBindingUtil.setContentView(this,R.layout.activity_search)
        with(binding) {
            val application = application as MyApplication
            val retrofitBuilder = application.retrofit
            val apiInterface = retrofitBuilder.create(Api::class.java)
            val repository = Repository(apiInterface)

            viewModel = ViewModelProvider(
                this@SearchActivity,
                MyViewModelFactory(repository)
            )[MyViewModel::class.java]

            searchView.addTextChangedListener (object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable?) {
                    if (s.toString().isEmpty()) {
                        rvChild.visibility = View.GONE
                    } else {
                        rvChild.visibility = View.VISIBLE
                    }
                    viewModel.search(s.toString()).observe(this@SearchActivity) {
//                binding.shimmerLayout.stopShimmer()
//                binding.shimmerLayout.visibility= View.GONE
//                binding.mainLayout.visibility= View.VISIBLE

                        searchTitle.text="Result for:- ${s.toString()}"
                        if (it!=null){
                            val adapter = HomeItemAdapter(it.contents, this@SearchActivity)
                            rvChild.adapter = adapter
                        }



                    }
                }
            })



        }

    }
}