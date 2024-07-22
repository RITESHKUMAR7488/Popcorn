package com.example.imdb.uis.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.imdb.R
import com.example.imdb.databinding.FragmentDescriptionBinding
import com.example.imdb.models.MovieList
import com.example.imdb.viewModels.MyViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo


class Description(val movie: MovieList) : Fragment() {
    private lateinit var binding: FragmentDescriptionBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_description, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("nk/data", "$movie")

        with(binding) {
            tvDescription.text = movie.overview
            lifecycle.addObserver(youtubePlayer)


            val listener = object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    // using pre-made custom ui


                    val videoID = movie.youtube_trailer?.let { getYouTubeID(it) }

                    activity?.let {
                        if (videoID != null) {
                            youTubePlayer.loadVideo( videoID, 0f)
                        }
                    }
                }

            }

            // disable web ui
            val options: IFramePlayerOptions =
                IFramePlayerOptions.Builder().controls(0).build()

            try {
                youtubePlayer.initialize(listener, options)

                youtubePlayer.getYouTubePlayerWhenReady(object :
                    YouTubePlayerCallback {

                    override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.addListener(object :
                            AbstractYouTubePlayerListener() {
                            override fun onStateChange(
                                youTubePlayer: YouTubePlayer,
                                state: PlayerConstants.PlayerState
                            ) {

                            }
                        })
                    }
                })
            } catch (e: Exception) {
                youtubePlayer.addYouTubePlayerListener(listener)
                Log.e("TAG", e.message.toString())
            }
        }
    }

    fun getYouTubeID(url: String): String {
        var videoID = when {
            url.contains("watch") -> {
                if (url.split("v=".toRegex()).size > 1) {
                    url.split("v=".toRegex()).toTypedArray()[1]
                } else {
                    ""
                }
            }

            url.contains("youtu.be") -> {
                if (url.split(".be/".toRegex()).size > 1) {
                    url.split(".be/".toRegex()).toTypedArray()[1]
                } else {
                    ""
                }
            }

            url.contains("live") -> {
                if (url.split("live/".toRegex()).size > 1) {
                    url.split("live/".toRegex()).toTypedArray()[1]
                } else {
                    ""
                }
            }

            url.contains("shorts") -> {
                if (url.split("shorts/".toRegex()).size > 1) {
                    url.split("shorts/".toRegex()).toTypedArray()[1]
                } else {
                    ""
                }
            }

            else -> {
                ""
            }
        }

        if (videoID.contains("?")) {
            val videoIDSplit = videoID.split("?")
            videoID = videoIDSplit[0]
        }

        Log.i("youtubeVideoID", videoID)

        return videoID
    }


}