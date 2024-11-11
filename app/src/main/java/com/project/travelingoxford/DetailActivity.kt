package com.project.travelingoxford

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val destination = intent.getParcelableExtra<Destination>("destination")

        destination?.let {
            findViewById<ImageView>(R.id.destination_image).setImageResource(it.imageResourceId)
            findViewById<TextView>(R.id.destination_name).text = it.name
            findViewById<TextView>(R.id.destination_description).text = it.description
        }

        findViewById<Button>(R.id.back_button).setOnClickListener {
            finish()
        }
    }
}
