package com.mycutpro

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.VideoView
import android.widget.MediaController

class MainActivity : Activity() {

    private lateinit var videoView: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(24, 24, 24, 24)
            setBackgroundColor(Color.rgb(18, 18, 24))
        }

        val title = TextView(this).apply {
            text = "MyCut Pro"
            textSize = 30f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }

        val subtitle = TextView(this).apply {
            text = "Your video editor"
            textSize = 18f
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
        }

        videoView = VideoView(this).apply {
            setMediaController(MediaController(this@MainActivity))
        }

        val chooseButton = Button(this).apply {
            text = "Choose Video"
            setOnClickListener {
                val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                    type = "video/*"
                }
                startActivityForResult(intent, 100)
            }
        }

        layout.addView(title)
        layout.addView(subtitle)

        layout.addView(
            videoView,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        layout.addView(chooseButton)

        setContentView(layout)
    }

    @Deprecated("Deprecated in Android, but supported")
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == RESULT_OK) {
            val videoUri = data?.data

            if (videoUri != null) {
                videoView.setVideoURI(videoUri)
                videoView.start()
            }
        }
    }
}
