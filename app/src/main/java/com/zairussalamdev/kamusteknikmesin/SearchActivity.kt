package com.zairussalamdev.kamusteknikmesin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.zairussalamdev.kamusteknikmesin.adapter.KategoriAdapter
import com.zairussalamdev.kamusteknikmesin.db.db
import com.zairussalamdev.kamusteknikmesin.model.Kategori
import kotlinx.android.synthetic.main.activity_kategori.*
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSupportActionBar(searchToolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var items : List<Kategori> = mutableListOf()
        db.use{
            val res = select(Kategori.TABLE_KATEGORI)
            items = res.parseList(classParser())
        }
        rvSearch.layoutManager = LinearLayoutManager(this)
        rvSearch.setHasFixedSize(true)
        rvSearch.adapter = KategoriAdapter(this, items){
            Log.d("Materi", it.id_kategori.toString())
            startActivity<MateriActivity>("id_kategori" to it.id_kategori)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home)super.onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}
