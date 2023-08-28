package com.example.real_estate

import android.content.Intent
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.*

class AdminDashboard : AppCompatActivity() {

    private val Imageback = 1

    private var Folder: StorageReference? = null
    var edproname: EditText? = null
    var edarea: EditText? = null
    var edaddress: EditText? = null
    var edprice: EditText? = null
    var edcontact: EditText? = null


    var bname: String? = null
    var proname: String? = null
    var address: String? = null
    var contact: String? = null
    var area:String?=null
    var price:String?=null






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)


        edproname = findViewById(R.id.edproname)
        edaddress = findViewById(R.id.edaddress)
        edcontact = findViewById(R.id.edcontact)
        edarea = findViewById(R.id.edarea)
        edprice = findViewById(R.id.edprice)


        Folder = FirebaseStorage.getInstance().reference.child("ImageFolder")

    }
    fun UploadData(view: View?) {
        storedata()
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, Imageback)
    }
    private fun storedata() {

        proname = edproname!!.text.toString()
        address = edaddress!!.text.toString()
        contact = edcontact!!.text.toString()
        price = edprice!!.text.toString()
        area = edarea!!.text.toString()





    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Imageback) {
            if (resultCode == RESULT_OK) {
                val ImageData = data!!.data
                val Imagename = Folder!!.child("image" + ImageData!!.lastPathSegment)
                Imagename.putFile(ImageData).addOnSuccessListener {
                    Imagename.downloadUrl.addOnSuccessListener { uri ->
                        val Imagestore = FirebaseDatabase.getInstance().reference.child("data")
                        val items = Items(proname,area,address,contact,price,uri.toString())


                        Imagestore.push().setValue(items)
                        Toast.makeText(applicationContext,"Uploaded", Toast.LENGTH_LONG).show()


                    }
                }
            }
        }


    }

}