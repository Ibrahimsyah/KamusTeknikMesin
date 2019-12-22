package com.zairussalamdev.kamusteknikmesin.view

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.zairussalamdev.kamusteknikmesin.R
import com.zairussalamdev.kamusteknikmesin.adapter.KategoriAdapter
import com.zairussalamdev.kamusteknikmesin.model.Kategori
import com.zairussalamdev.kamusteknikmesin.presenter.KategoriPresenter
import com.zairussalamdev.kamusteknikmesin.presenter.KategoriView
import kotlinx.android.synthetic.main.activity_kategori.*
import org.jetbrains.anko.startActivity

class KategoriActivity : AppCompatActivity(), KategoriView {

    override fun showLoading() {
        pbKategori.visibility = View.VISIBLE
    }

    override fun showData(kategoriList: List<Kategori>) {
        pbKategori.visibility = View.GONE
        if (kategoriList.isNotEmpty()) {
            rvKategori.adapter = KategoriAdapter(this, kategoriList) {
                Log.d("Materi", it.id_kategori.toString())
                startActivity<MateriActivity>("kategori" to it)
            }

        } else {
            kategoriNoResult.visibility = View.VISIBLE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kategori)

        setSupportActionBar(kategoriToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val presenter = KategoriPresenter(this, this)
        presenter.getKategori()
        rvKategori.layoutManager = LinearLayoutManager(this)
        rvKategori.setHasFixedSize(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) super.onBackPressed()
        return super.onOptionsItemSelected(item)
    }

}
