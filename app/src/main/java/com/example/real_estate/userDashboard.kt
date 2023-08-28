package com.example.real_estate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class userDashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_dashboard)


        val btn = findViewById<Button>(R.id.btnlist)

        btn.setOnClickListener {

            val intent = Intent(applicationContext,usershowlist::class.java)
            startActivity(intent)
        }

    }
}