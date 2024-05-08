package com.example.assignmentst

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import java.security.PrivateKey

class MainActivity : AppCompatActivity() {
    private var eat = 50
    private var clean = 50
    private var play = 60
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var btnplay: Button
    private lateinit var btnfeed: Button
    private lateinit var btnclean: Button
    private lateinit var textplay: EditText
    private lateinit var textfeed: EditText
    private lateinit var textclean: EditText
    private lateinit var petimage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI elements
        btnplay = findViewById(R.id.btnplay)
        btnfeed = findViewById(R.id.btnfeed)
        btnclean = findViewById(R.id.btnclean)
        textplay = findViewById(R.id.textplay)
        textfeed = findViewById(R.id.textfeed)
        textclean = findViewById(R.id.textclean)
        petimage = findViewById(R.id.petimage)

        // Set initial values
        textfeed.setText(eat.toString())
        textclean.setText(clean.toString())
        textplay.setText(play.toString())

        // Start the decrement timer
        startDecrementTimer()

        btnplay.setOnClickListener {
            play += 10
            play = play.coerceIn(0, 100)
            textplay.setText(play.toString())
            animateImageChange(petimage, R.drawable.happy)
        }

        btnclean.setOnClickListener {
            clean += 10
            clean = clean.coerceIn(0, 100)
            play += 5
            play = play.coerceIn(0, 100)
            textclean.setText(clean.toString())
            textplay.setText(play.toString())
            animateImageChange(petimage, R.drawable.clean)
        }

        btnfeed.setOnClickListener {
            eat -= 10
            eat = eat.coerceIn(0, 100)
            play += 5
            play = play.coerceIn(0, 100)
            textfeed.setText(eat.toString())
            textplay.setText(play.toString())
            animateImageChange(petimage, R.drawable.full)
        }
    }

    private fun animateImageChange(imageView: ImageView, drawableId: Int) {
        val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.image_change_animation)
        imageView.startAnimation(animation)
        imageView.setImageResource(drawableId)
    }

    private fun startDecrementTimer() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                // Decrement pet stats periodically
                eat += 10
                clean -= 10
                play -= 10

                // Update UI on the main thread
                runOnUiThread {
                    textfeed.setText(eat.toString())
                    textclean.setText(clean.toString())
                    textplay.setText(play.toString())
                }

                // Schedule the next decrement after a delay
                handler.postDelayed(this, 10000) // Adjust the delay as needed (e.g., 10000 milliseconds = 10 seconds)
            }
        }, 10000) // Start the decrement timer after an initial delay (e.g., 10000 milliseconds = 10 seconds)
    }
}















