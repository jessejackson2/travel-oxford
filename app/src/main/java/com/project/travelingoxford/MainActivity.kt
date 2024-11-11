package com.project.travelingoxford

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var radioGroup: RadioGroup
    private lateinit var destinationSpinner: Spinner
    private lateinit var submitButton: Button
    private lateinit var clearButton: Button
    private lateinit var displayArea: TextView

    private val boatDestinations = listOf(
        Destination("Oxford River Cruise", "Enjoy a scenic cruise along the Oxford River.", R.drawable.oxford_river_cruise),
        Destination("Thames River Punting", "Experience traditional punting on the Thames River.", R.drawable.thames_river_punting),
        Destination("Magdalen Bridge Tour", "Explore the historic Magdalen Bridge.", R.drawable.magdalen_bridge_tour)
    )

    private val trainDestinations = listOf(
        Destination("London Paddington", "Travel to the heart of London.", R.drawable.london_paddington),
        Destination("Birmingham New Street", "Visit the bustling city of Birmingham.", R.drawable.birmingham_new_street),
        Destination("Reading", "Discover the charming town of Reading.", R.drawable.reading)
    )

    private val airplaneDestinations = listOf(
        Destination("Heathrow Airport", "Fly to one of the world's busiest airports.", R.drawable.heathrow_airport),
        Destination("Gatwick Airport", "Travel through London's second largest airport.", R.drawable.gatwick_airport),
        Destination("Manchester Airport", "Experience the vibrant city of Manchester.", R.drawable.manchester_airport)
    )

    private var selectedTravelMode = ""
    private var currentDestinations = listOf<Destination>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioGroup = findViewById(R.id.radioGroup)
        destinationSpinner = findViewById(R.id.destination_spinner)
        submitButton = findViewById(R.id.submit_button)
        clearButton = findViewById(R.id.clear_button)
        displayArea = findViewById(R.id.display_area)

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.boat -> {
                    selectedTravelMode = "Boat"
                    updateSpinner(boatDestinations)
                }
                R.id.train -> {
                    selectedTravelMode = "Train"
                    updateSpinner(trainDestinations)
                }
                R.id.airplane -> {
                    selectedTravelMode = "Airplane"
                    updateSpinner(airplaneDestinations)
                }
            }
        }

        submitButton.setOnClickListener { submitSelection() }
        clearButton.setOnClickListener { clearSelection() }
    }

    private fun updateSpinner(destinations: List<Destination>) {
        currentDestinations = destinations
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, destinations.map { it.name })
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        destinationSpinner.adapter = adapter
    }

    private fun submitSelection() {
        val selectedPosition = destinationSpinner.selectedItemPosition
        if (selectedPosition >= 0) {
            val selectedDestination = currentDestinations[selectedPosition]
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("destination", selectedDestination)
            }
            startActivity(intent)
        }
    }

    private fun clearSelection() {
        radioGroup.clearCheck()
        destinationSpinner.setSelection(0)
        displayArea.text = ""
    }
}
