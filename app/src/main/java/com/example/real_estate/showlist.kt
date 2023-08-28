package com.example.real_estate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.*
import java.util.ArrayList

class showlist : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private val db = FirebaseDatabase.getInstance()
    private val root = db.reference.child("data")

    private var adapter: MyAdapter? = null
    private var list: ArrayList<Items?>? = null
    var ref: DatabaseReference? = null
    var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showlist)
        val recyclerView = findViewById<RecyclerView>(R.id.recylerview)
        recyclerView.setHasFixedSize(true)
        recyclerView.setLayoutManager(LinearLayoutManager(this))


        val options: FirebaseRecyclerOptions<Items> = FirebaseRecyclerOptions.Builder<Items>()
            .setQuery(FirebaseDatabase.getInstance().getReference().child("data"), Items::class.java)
            .build()

        adapter = MyAdapter(options,this)
        recyclerView.setAdapter(adapter)





    }

    override fun onStart() {
        super.onStart()
        adapter!!.startListening()
    }
    override fun onStop() {
        super.onStop()
        adapter!!.stopListening()
    }



}