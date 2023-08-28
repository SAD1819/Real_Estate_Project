package com.example.real_estate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import java.util.ArrayList

class usershowlist : AppCompatActivity() {


    var ref: DatabaseReference? = null
    var list: ArrayList<Items>? = null
    private var listener: Adapter.RecyclerViewClickListener? = null

    var recyclerView: RecyclerView? = null

    var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usershowlist)

        ref = FirebaseDatabase.getInstance().reference.child("data")
        recyclerView = findViewById(R.id.recyclerview)
        searchView = findViewById(R.id.searchview)

    }
    override fun onStart() {
        super.onStart()
        if (ref != null) {
            ref!!.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        list = ArrayList()
                        for (ds in snapshot.children) {
                            list!!.add(ds.getValue(Items::class.java)!!)
                        }
                        setOnClickListner()
                        val adapter = Adapter(list, listener)
                        recyclerView!!.adapter = adapter
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@usershowlist, "error", Toast.LENGTH_SHORT).show()
                }
            })
        }


        if (searchView != null) {
            searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(s: String): Boolean {
                    return false
                }

                override fun onQueryTextChange(s: String): Boolean {
                    search(s)
                    return true
                }
            })
        }
    }

    private fun setOnClickListner() {
        listener = Adapter.RecyclerViewClickListener { v, position ->
            val intent = Intent(applicationContext, userdetails::class.java)
            intent.putExtra("proname", list!![position].propertyname)
            intent.putExtra("address",list!![position].address)
            intent.putExtra("area",list!![position].area)
            intent.putExtra("price",list!![position].price)
            intent.putExtra("contactno",list!![position].price)
            intent.putExtra("url",list!![position].imageurl)


            startActivity(intent)
        }
    }

    private fun search(s: String) {
//        Toast.makeText(applicationContext,s.toString(),Toast.LENGTH_LONG).show()
        try{
            val mylist = ArrayList<Items?>()
        for (`object` in list!!) {
            if (`object`!!.getAddress().toLowerCase().contains(s.toLowerCase())) {
                mylist.add(`object`)
            }
        }
       val adapter = Adapter(mylist,listener)
        recyclerView!!.adapter = adapter
        }catch (e:Exception){
            e.printStackTrace()
        }

    }
}