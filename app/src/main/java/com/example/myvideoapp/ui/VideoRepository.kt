package com.example.myvideoapp.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VideoRepository {

    private val mp4Url = "https://html5demos.com/assets/dizzy.mp4"
    private val dashUrl = "https://storage.googleapis.com/wvmedia/clear/vp9/tears/tears_uhd.mpd"
    private val mp4Url1 = "https://res.cloudinary.com/dxv9lvcn2/video/upload/v1620721290/%E0%A4%AE%E0%A4%BF%E0%A4%A4%E0%A5%8D%E0%A4%B0_%E0%A4%B5%E0%A4%A3%E0%A4%B5%E0%A5%8D%E0%A4%AF%E0%A4%BE%E0%A4%AE%E0%A4%A7%E0%A5%8D%E0%A4%AF%E0%A5%87_%E0%A4%97%E0%A4%BE%E0%A4%B0%E0%A4%B5%E0%A5%8D%E0%A4%AF%E0%A4%BE%E0%A4%B8%E0%A4%BE%E0%A4%B0%E0%A4%96%E0%A4%BE_-_%E0%A4%8B%E0%A4%B7%E0%A4%BF%E0%A4%95%E0%A5%87%E0%A4%B6_%E0%A4%B0%E0%A4%BF%E0%A4%95%E0%A4%BE%E0%A4%AE%E0%A5%87_%E0%A4%95%E0%A4%B5%E0%A5%80_%E0%A4%85%E0%A4%A8%E0%A4%82%E0%A4%A4_%E0%A4%B0%E0%A4%BE%E0%A4%8A%E0%A4%A4_marathi_ytshorts_rushirikame_%E0%A4%AE%E0%A4%BF%E0%A4%A4%E0%A5%8D%E0%A4%B0_n4bc9j.mkv"


    // 1 Row - Data
    private val mp4TitleFirst = "For Bigger Blazes"
   // private val mp4UrlFirst = "https://html5demos.com/assets/dizzy.mp4"
    private val mp4UrlFirst = "https://res.cloudinary.com/dxv9lvcn2/video/upload/v1620721290/%E0%A4%AE%E0%A4%BF%E0%A4%A4%E0%A5%8D%E0%A4%B0_%E0%A4%B5%E0%A4%A3%E0%A4%B5%E0%A5%8D%E0%A4%AF%E0%A4%BE%E0%A4%AE%E0%A4%A7%E0%A5%8D%E0%A4%AF%E0%A5%87_%E0%A4%97%E0%A4%BE%E0%A4%B0%E0%A4%B5%E0%A5%8D%E0%A4%AF%E0%A4%BE%E0%A4%B8%E0%A4%BE%E0%A4%B0%E0%A4%96%E0%A4%BE_-_%E0%A4%8B%E0%A4%B7%E0%A4%BF%E0%A4%95%E0%A5%87%E0%A4%B6_%E0%A4%B0%E0%A4%BF%E0%A4%95%E0%A4%BE%E0%A4%AE%E0%A5%87_%E0%A4%95%E0%A4%B5%E0%A5%80_%E0%A4%85%E0%A4%A8%E0%A4%82%E0%A4%A4_%E0%A4%B0%E0%A4%BE%E0%A4%8A%E0%A4%A4_marathi_ytshorts_rushirikame_%E0%A4%AE%E0%A4%BF%E0%A4%A4%E0%A5%8D%E0%A4%B0_n4bc9j.mkv"
    private val mp4ThumbFirst = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerBlazes.jpg"

    // 2 Row - Data
    private val mp4TitleSecond = "For Bigger Fun"
    private val mp4UrlSecond = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"
    private val mp4ThumbSecond = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerFun.jpg"

    // 3 Row - Data
    private val mp4TitleThird = "Subaru Outback On Street And Dirt"
    private val mp4UrlThird = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4"
    private val mp4ThumbThird = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/SubaruOutbackOnStreetAndDirt.jpg"

    // 4 Row - Data
    private val mp4TitleFour = "Volkswagen GTI Review"
    private val mp4UrlFour = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/VolkswagenGTIReview.mp4"
    private val mp4ThumbFour = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/VolkswagenGTIReview.jpg"


    fun showVideo() : List<VideoModal> {

        val list : List<VideoModal> = listOf(
                VideoModal("1",mp4TitleFirst,mp4UrlFirst,mp4ThumbFirst),
                VideoModal("2",mp4TitleSecond,mp4UrlSecond,mp4ThumbSecond),
                VideoModal("3",mp4TitleThird,mp4UrlThird,mp4ThumbThird),
                VideoModal("4",mp4TitleFour,mp4UrlFour,mp4ThumbFour))


        return list

    }






}