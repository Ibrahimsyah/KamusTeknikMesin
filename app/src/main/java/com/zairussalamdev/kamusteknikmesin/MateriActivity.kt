package com.zairussalamdev.kamusteknikmesin

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.zairussalamdev.kamusteknikmesin.adapter.MateriAdapter
import com.zairussalamdev.kamusteknikmesin.model.Materi
import kotlinx.android.synthetic.main.activity_materi.*

class MateriActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materi)

        val idKategori = intent.getIntExtra("id_kategori", 0).toString()
        Log.d("Materi", "Kategori: $idKategori")
        val items: MutableList<Materi> = mutableListOf()

        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("/materi").orderByChild("id_kategori").startAt(idKategori)
            .endAt(idKategori)
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}

            override fun onDataChange(p0: DataSnapshot) {
                for (h in p0.children) {
                    val item = h.getValue(Materi::class.java)
                    item?.let { items.add(item)}
                }
            }
        })
        rvMateri.layoutManager = LinearLayoutManager(this)
        rvMateri.setHasFixedSize(true)
        rvMateri.adapter = MateriAdapter(this, items){}
    }
}
