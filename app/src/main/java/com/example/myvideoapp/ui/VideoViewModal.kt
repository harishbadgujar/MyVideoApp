package com.example.myvideoapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VideoViewModal() : ViewModel( ) {

    var userLiveData = MutableLiveData<List<VideoModal>>()


    fun getData(){

        userLiveData.value =  VideoRepository().showVideo()

    }


}

