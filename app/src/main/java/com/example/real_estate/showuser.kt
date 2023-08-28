package com.example.real_estate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import java.util.ArrayList

class showuser : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private val db = FirebaseDatabase.getInstance()
    private val root = db.reference.child("user")

    private var adapter: UserAdapter? = null
    private var list: ArrayList<User>? = null
    var ref: DatabaseReference? = null
    var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showuser)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.setHasFixedSize(true)
        recyclerView.setLayoutManager(LinearLayoutManager(this))


        list = ArrayList()
//        adapter = MyAdapter(list,applicationContext)
        adapter = UserAdapter(list,applicationContext)
        recyclerView.setAdapter(adapter)

    }

    override fun onStart() {
        super.onStart()
        root.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children) {
                    val items = dataSnapshot.getValue(
                        User::class.java
                    )
                    if (items != null) {
                        list!!.add(items)
                    }
                }
                adapter!!.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {}
        })

    }
}