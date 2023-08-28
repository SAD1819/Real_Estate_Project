package com.example.real_estate

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.bumptech.glide.Glide

class userdetails : AppCompatActivity() {


    var proname:String?=null
    var address:String?=null
    var area:String?=null
    var price:String?=null
    var contactno:String?=null

    var url:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userdetails)

        val txtproname = findViewById<TextView>(R.id.txtproname)
        val txtcontact = findViewById<TextView>(R.id.txtconatct)
        val txtaddress = findViewById<TextView>(R.id.txtaddress)
        val txtprice = findViewById<TextView>(R.id.txtprice)
        val txtarea = findViewById<TextView>(R.id.txtarea)

        val image = findViewById<ImageView>(R.id.image1)
        val mSharedPreference = PreferenceManager.getDefaultSharedPreferences(baseContext)
        val value = mSharedPreference.getString("student-email", "DEFAULT")

        val btn = findViewById<Button>(R.id.btnupdate)
        btn.setOnClickListener {
            val se = send(applicationContext,value.toString(),"Your New Enquiry","I Am Interested To Buy Your Property ")
            se.execute()



        }

        val btnpay = findViewById<Button>(R.id.btnpay)
        btnpay.setOnClickListener {
            Toast.makeText(applicationContext,"Payment Successfully",Toast.LENGTH_LONG).show()
        }
        val bundle = intent.extras

        proname = bundle?.getString("proname")
        address = bundle?.getString("address")
        area=bundle?.getString("area")
        price = bundle?.getString("price")
        contactno = bundle?.getString("contactno")
        url = bundle?.getString("url")


        Glide.with(this@userdetails).load(url).into(image)


        txtproname.setText("Properitor Name:" +proname)
        txtaddress.setText("Address: "+address)
        txtarea.setText("Area: "+area)
        txtprice.setText("Price: "+price)

        txtcontact.setText("Contact: "+contactno)


    }


}