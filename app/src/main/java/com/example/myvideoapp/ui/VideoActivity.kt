package com.example.myvideoapp.ui

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myvideoapp.R
import com.example.myvideoapp.databinding.ActivityVideoViewBinding
import com.example.myvideoapp.ui.adapter.VideoViewAdapter
import kotlinx.android.synthetic.main.activity_video_view.*

class VideoActivity : AppCompatActivity() {

    private lateinit var videoAdapter: VideoViewAdapter
    private lateinit var viewModel: VideoViewModal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityVideoViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_video_view)

        initViewModel()
        observeData()
        binding.viewmodel = viewModel
        viewModel.getData()


    }

    private fun initViewModel() {

        viewModel = ViewModelProvider(this).get(VideoViewModal::class.java)
    }

    private fun observeData() {

        viewModel.userLiveData.observe(this, Observer {

            videoAdapter = VideoViewAdapter(this, it)

            if (resources.getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

                var layoutManager = GridLayoutManager(this, 2)
                recyclerView.layoutManager = layoutManager

            } else {

                var layoutManager = LinearLayoutManager(this)
                recyclerView.layoutManager = layoutManager

            }


            recyclerView.adapter = videoAdapter

        })

    }


}