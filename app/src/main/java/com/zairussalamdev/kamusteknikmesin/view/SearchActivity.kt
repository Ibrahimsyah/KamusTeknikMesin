package com.zairussalamdev.kamusteknikmesin.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.zairussalamdev.kamusteknikmesin.R
import com.zairussalamdev.kamusteknikmesin.adapter.KategoriAdapter
import com.zairussalamdev.kamusteknikmesin.adapter.MateriAdapter
import com.zairussalamdev.kamusteknikmesin.db.db
import com.zairussalamdev.kamusteknikmesin.model.Kategori
import com.zairussalamdev.kamusteknikmesin.model.Materi
import com.zairussalamdev.kamusteknikmesin.presenter.SearchPresenter
import com.zairussalamdev.kamusteknikmesin.presenter.SearchViews
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity

class SearchActivity : AppCompatActivity(), SearchViews {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSupportActionBar(searchToolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var items: List<Kategori> = mutableListOf()
        db.use {
            val res = select(Kategori.TABLE_KATEGORI)
            items = res.parseList(classParser())
        }
        val presenter = SearchPresenter(applicationContext, this)
        rvSearch.layoutManager = LinearLayoutManager(this)
        rvSearch.setHasFixedSize(true)
        rvSearch.adapter = KategoriAdapter(this, items) {
            startActivity<MateriActivity>("kategori" to it)
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    if (newText.isEmpty())
                        rvSearch.adapter = KategoriAdapter(applicationContext, items) {
                            startActivity<MateriActivity>("kategori" to it)
                        }
                    else {
                        rvSearch.adapter = MateriAdapter(applicationContext, mutableListOf()) {}
                        presenter.findMateri(newText)
                    }
                }
                return false
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) super.onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun showLoader() {
        searchNoResult.visibility = View.GONE
        pbSearch.visibility = View.VISIBLE
    }

    override fun showData(materis: List<Materi>) {
        pbSearch.visibility = View.GONE
        if (materis.isNotEmpty()) rvSearch.adapter = MateriAdapter(applicationContext, materis) {
            startActivity<DetailActivity>("id_materi" to it.id_materi)
        } else {
            searchNoResult.visibility = View.VISIBLE
        }
    }
}
