package com.example.real_estate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val upload = findViewById<Button>(R.id.btnupload)
        upload.setOnClickListener {
            val intent = Intent(applicationContext,AdminDashboard::class.java)
            startActivity(intent)
        }

        val update = findViewById<Button>(R.id.btnupdate)
        update.setOnClickListener {
            val intent = Intent(applicationContext,showlist::class.java)
            startActivity(intent)
        }

        val user = findViewById<Button>(R.id.btnuser)
        user.setOnClickListener {
            val intent = Intent(applicationContext,showuser::class.java)
            startActivity(intent)
        }
    }
}