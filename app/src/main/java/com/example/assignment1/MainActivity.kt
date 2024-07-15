package com.example.assignment1

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var planetDetailsTextView: TextView
    private lateinit var selectedPlanet: String
    private lateinit var progressContainer: FrameLayout
    private lateinit var backgroundImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Button Task
        val myButton: Button = findViewById(R.id.myButton)

//        Spinner Task
        val mySpinner: Spinner = findViewById(R.id.mySpinner)
        planetDetailsTextView = findViewById(R.id.planetDetails)
        progressContainer = findViewById""(R.id.progressContainer)
        backgroundImageView = findViewById(R.id.backgroundImageView)

        val adapter = ArrayAdapter.createFromResource(this,
            R.array.spinner_items, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mySpinner.adapter = adapter
//        onItemSelectedListener Task
        mySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedPlanet = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                selectedPlanet = ""
            }
        }
//        setOnClickListener Task
        myButton.setOnClickListener {
            progressContainer.visibility = View.VISIBLE
            planetDetailsTextView.text = ""

            // Button Animation
            val buttonAnimation = AlphaAnimation(0.1f, 1.0f)
            buttonAnimation.duration = 300
            myButton.startAnimation(buttonAnimation)

            Handler(Looper.getMainLooper()).postDelayed({
                val details = when (selectedPlanet) {
                    "Mercury" -> getString(R.string.mercury_details)
                    "Venus" -> getString(R.string.venus_details)
                    "Earth" -> getString(R.string.earth_details)
                    "Mars" -> getString(R.string.mars_details)
                    "Jupiter" -> getString(R.string.jupiter_details)
                    "Saturn" -> getString(R.string.saturn_details)
                    "Uranus" -> getString(R.string.uranus_details)
                    "Neptune" -> getString(R.string.neptune_details)
                    else -> "Select a planet to see the details."
                }
                val imageRes = when (selectedPlanet) {
                    "Mercury" -> R.drawable.mercury
                    "Venus" -> R.drawable.venus
                    "Earth" -> R.drawable.earth
                    "Mars" -> R.drawable.mars
                    "Jupiter" -> R.drawable.jupiter
                    "Saturn" -> R.drawable.saturn
                    "Uranus" -> R.drawable.uranus
                    "Neptune" -> R.drawable.neptune
                    else -> R.drawable.space_background
                }
                backgroundImageView.setImageResource(imageRes)
                planetDetailsTextView.text = details
                progressContainer.visibility = View.GONE
            }, 1500) // 1.5 seconds delay
        }

        // Apply shadow and glow effect
        planetDetailsTextView.setShadowLayer(10f, 0f, 0f, Color.MAGENTA)

        // Create a glowing effect animation
        val glowAnimation = AlphaAnimation(0.3f, 1.0f)
        glowAnimation.duration = 1000
        glowAnimation.repeatMode = Animation.REVERSE
        glowAnimation.repeatCount = Animation.INFINITE
        planetDetailsTextView.startAnimation(glowAnimation)
    }
}
