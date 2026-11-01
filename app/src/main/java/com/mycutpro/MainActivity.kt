package com.mycutpro

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(32, 32, 32, 32)
            setBackgroundColor(Color.rgb(18, 18, 24))
        }

        val title = TextView(this).apply {
            text = "MyCut Pro"
            textSize = 32f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }

        val subtitle = TextView(this).apply {
            text = "Your video editor"
            textSize = 18f
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
        }

        val chooseButton = Button(this).apply {
            text = "Choose Video"
            setOnClickListener {
                val intent = android.content.Intent(
                    android.content.Intent.ACTION_GET_CONTENT
                )
                intent.type = "video/*"
                startActivityForResult(intent, 100)
            }
        }

        layout.addView(title)
        layout.addView(subtitle)
        layout.addView(chooseButton)

        setContentView(layout)
    }
}
