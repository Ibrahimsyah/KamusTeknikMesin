package com.zairussalamdev.kamusteknikmesin.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.zairussalamdev.kamusteknikmesin.R
import com.zairussalamdev.kamusteknikmesin.adapter.KategoriAdapter
import com.zairussalamdev.kamusteknikmesin.db.db
import com.zairussalamdev.kamusteknikmesin.model.Kategori
import kotlinx.android.synthetic.main.activity_kategori.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity

class KategoriActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kategori)

        var items: List<Kategori> = mutableListOf()
        db.use {
            val res = select(Kategori.TABLE_KATEGORI)
            items = res.parseList(classParser())
        }
        rvKategori.layoutManager = LinearLayoutManager(this)
        rvKategori.setHasFixedSize(true)
        rvKategori.adapter = KategoriAdapter(this, items) {
            Log.d("Materi", it.id_kategori.toString())
            startActivity<MateriActivity>("id_kategori" to it.id_kategori)
        }
    }
}
