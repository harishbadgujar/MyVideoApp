package com.example.myvideoapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , Player.EventListener {
    private lateinit var simpleExoplayer: SimpleExoPlayer
    private var playbackPosition: Long = 0
    private val mp4Url = "  https://res.cloudinary.com/dxv9lvcn2/video/upload/v1620721290/%E0%A4%AE%E0%A4%BF%E0%A4%A4%E0%A5%8D%E0%A4%B0_%E0%A4%B5%E0%A4%A3%E0%A4%B5%E0%A5%8D%E0%A4%AF%E0%A4%BE%E0%A4%AE%E0%A4%A7%E0%A5%8D%E0%A4%AF%E0%A5%87_%E0%A4%97%E0%A4%BE%E0%A4%B0%E0%A4%B5%E0%A5%8D%E0%A4%AF%E0%A4%BE%E0%A4%B8%E0%A4%BE%E0%A4%B0%E0%A4%96%E0%A4%BE_-_%E0%A4%8B%E0%A4%B7%E0%A4%BF%E0%A4%95%E0%A5%87%E0%A4%B6_%E0%A4%B0%E0%A4%BF%E0%A4%95%E0%A4%BE%E0%A4%AE%E0%A5%87_%E0%A4%95%E0%A4%B5%E0%A5%80_%E0%A4%85%E0%A4%A8%E0%A4%82%E0%A4%A4_%E0%A4%B0%E0%A4%BE%E0%A4%8A%E0%A4%A4_marathi_ytshorts_rushirikame_%E0%A4%AE%E0%A4%BF%E0%A4%A4%E0%A5%8D%E0%A4%B0_n4bc9j.mkv"
  //  private val mp4Url = "https://html5demos.com/assets/dizzy.mp4"
    private val dashUrl = "https://storage.googleapis.com/wvmedia/clear/vp9/tears/tears_uhd.mpd"
   // private val dashUrl = "https://www.youtube.com/watch?v=nLU7FwntBmY.mp4"
    private val urlList = listOf(mp4Url to "default", dashUrl to "dash")

    private val dataSourceFactory: DataSource.Factory by lazy {
        DefaultDataSourceFactory(this, "exoplayer-sample")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      //  use link - https://blog.mindorks.com/using-exoplayer-to-play-video-and-audio-in-android-like-a-pro
    }

    override fun onStart() {
        super.onStart()
        initializePlayer()
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    private fun initializePlayer() {
        simpleExoplayer = SimpleExoPlayer.Builder(this).build()
        val randomUrl = urlList.random()
        preparePlayer(randomUrl.first, randomUrl.second)
        exoplayerView.player = simpleExoplayer
        simpleExoplayer.seekTo(playbackPosition)
        simpleExoplayer.playWhenReady = true
        simpleExoplayer.addListener(this)
    }

    private fun buildMediaSource(uri: Uri, type: String): MediaSource {
        return if (type == "dash") {
            DashMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(uri)
        } else {
            ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(uri)
        }
    }

    private fun preparePlayer(videoUrl: String, type: String) {
        val uri = Uri.parse(videoUrl)
        val mediaSource = buildMediaSource(uri, type)
        simpleExoplayer.prepare(mediaSource)
    }

    private fun releasePlayer() {
        playbackPosition = simpleExoplayer.currentPosition
        simpleExoplayer.release()
    }

    override fun onPlayerError(error: ExoPlaybackException) {
        // handle error
    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        if (playbackState == Player.STATE_BUFFERING)
            progressBar.visibility = View.VISIBLE
        else if (playbackState == Player.STATE_READY || playbackState == Player.STATE_ENDED)
            progressBar.visibility = View.INVISIBLE
    }

}