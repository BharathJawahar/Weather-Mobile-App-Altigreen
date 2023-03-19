package com.example.altigreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.searchButton).setOnClickListener {
            val intent = Intent(this, Weather::class.java)
            intent.putExtra("cityName", findViewById<EditText>(R.id.cityText).text.toString())
            startActivity(intent)
        }
    }
}