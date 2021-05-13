package com.example.myvideoapp.ui.showview

import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.myvideoapp.R
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import kotlinx.android.synthetic.main.activity_main.exoplayerView
import kotlinx.android.synthetic.main.activity_main.progressBar
import kotlinx.android.synthetic.main.activity_video_stream.*


class VideoStreamActivity : AppCompatActivity() , Player.EventListener {
    private lateinit var simpleExoplayer: SimpleExoPlayer
    private var playbackPosition: Long = 0
    lateinit var showlink : String
    var isMute: Boolean = true
    var brightness : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_stream)

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        showlink  = intent.getStringExtra("link").toString()


        ivVolume.setOnClickListener(View.OnClickListener {

           // Toast.makeText(this,"sound Testing",Toast.LENGTH_LONG).show()

            if(isMute){

                isMute = false
                simpleExoplayer.pause()

                 ivVolume.setImageResource(R.drawable.ic_volume_off)
                 ivVolume.visibility = View.GONE
                 ivVolumeDown.visibility = View.VISIBLE


            }else{

                isMute = true

               // simpleExoplayer.playWhenReady(true)
                simpleExoplayer.playWhenReady
                ivVolume.visibility = View.GONE
                ivVolume.setImageResource(R.drawable.ic_volume_on)
                ivVolumeDown.visibility = View.VISIBLE

            }


        })

        ivVolumeDown.setOnClickListener(View.OnClickListener {


            if(isMute){

              //  Toast.makeText(this,"sound off",Toast.LENGTH_LONG).show()

                simpleExoplayer.pause()
                ivVolume.setImageResource(R.drawable.ic_volume_off)
                isMute = false

            }else{

               // Toast.makeText(this,"sound on",Toast.LENGTH_LONG).show()
                isMute = true
                simpleExoplayer.play()
                ivVolume.visibility = View.VISIBLE
                ivVolume.setImageResource(R.drawable.ic_volume_up)
                ivVolumeDown.visibility = View.GONE

            }


        })

        seekBarBrighNess()

    }

    private fun seekBarBrighNess() {
        brightness = Settings.System.getInt(
            this.getContentResolver(),
            Settings.System.SCREEN_BRIGHTNESS, 0
        )
        seekBar.setProgress(brightness)
        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar,
                progress: Int,
                fromUser: Boolean
            ) {
                Settings.System.putInt(
                    applicationContext.getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS, progress
                )
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }


    private val dataSourceFactory: DataSource.Factory by lazy {
        DefaultDataSourceFactory(this, "exoplayer-sample")
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
       // val randomUrl = urlList.random()
        preparePlayer(showlink,"dash1")
        exoplayerView.player = simpleExoplayer
        simpleExoplayer.seekTo(playbackPosition)
        simpleExoplayer.playWhenReady = true
        simpleExoplayer.addListener(this)
    }

    private fun preparePlayer(videoUrl: String, type: String) {
        val uri = Uri.parse(videoUrl)
        val mediaSource = buildMediaSource(uri, type)
        simpleExoplayer.prepare(mediaSource)
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

