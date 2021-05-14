package com.example.myvideoapp.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.example.myvideoapp.R
import com.example.myvideoapp.ui.VideoModal
import com.example.myvideoapp.ui.showview.VideoStreamActivity
import java.lang.reflect.Array.set
import javax.security.auth.callback.Callback

class VideoViewAdapter(var context: Context, var list: List<VideoModal>) : RecyclerView.Adapter<VideoViewAdapter.ViewHolder>() {

    var isMute: Boolean = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewAdapter.ViewHolder {

        return ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.video_child_row, parent, false)
        )
    }

    override fun getItemCount(): Int = list.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val context: Context = itemView.context

        val imgUrl: ImageView = itemView.findViewById(R.id.imgUrl)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val videoModal: VideoModal = list.get(position)


        if (videoModal.videoUrl !== null) {
            Glide.with(context)
                    .load(videoModal.videoUrl)
                    .into(holder.imgUrl)
        } else {
            holder.imgUrl.setImageResource(R.drawable.ic_launcher_background)
        }


        holder.itemView.setOnClickListener {

            val intent = Intent(context, VideoStreamActivity::class.java)
            intent.putExtra("link", videoModal.videoUrl)
            context.startActivity(intent);

        }


    }


}