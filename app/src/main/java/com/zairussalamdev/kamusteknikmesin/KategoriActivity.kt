package com.zairussalamdev.kamusteknikmesin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.zairussalamdev.kamusteknikmesin.adapter.KategoriAdapter
import com.zairussalamdev.kamusteknikmesin.model.Kategori
import kotlinx.android.synthetic.main.activity_kategori.*

class KategoriActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kategori)

        val items : MutableList<Kategori> = mutableListOf()
        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("/kategori")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}

            override fun onDataChange(p0: DataSnapshot) {
                for(h  in p0.children){
                    val item = h.getValue(Kategori::class.java)
                    item?.let { items.add(item) }
                }
            }
        })
        rvKategori.layoutManager = LinearLayoutManager(this)
        rvKategori.setHasFixedSize(true)
        rvKategori.adapter = KategoriAdapter(this, items){}
    }
}
