package com.zairussalamdev.kamusteknikmesin.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.zairussalamdev.kamusteknikmesin.R
import com.zairussalamdev.kamusteknikmesin.adapter.MateriAdapter
import com.zairussalamdev.kamusteknikmesin.model.Kategori
import com.zairussalamdev.kamusteknikmesin.model.Materi
import com.zairussalamdev.kamusteknikmesin.presenter.MateriPresenter
import com.zairussalamdev.kamusteknikmesin.presenter.MateriView
import kotlinx.android.synthetic.main.activity_materi.*
import org.jetbrains.anko.startActivity

class MateriActivity : AppCompatActivity(), MateriView {

    override fun showLoading() {
        pbMateri.visibility = View.VISIBLE
    }

    override fun showData(materiList: List<Materi>) {
        pbMateri.visibility = View.GONE
        if (materiList.isNotEmpty()) {
            rvMateri.adapter = MateriAdapter(this, materiList) {
                startActivity<DetailActivity>("id_materi" to it.id_materi)
            }

        } else {
            materiNoResult.visibility = View.VISIBLE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materi)

        val kategori = intent.getParcelableExtra<Kategori>("kategori")

        materiToolbar.title = "Kategori: ${kategori.name}"
        setSupportActionBar(materiToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val presenter = MateriPresenter(this, this)
        kategori.id_kategori?.let { presenter.getMateri(it) }

        rvMateri.layoutManager = LinearLayoutManager(this)
        rvMateri.setHasFixedSize(true)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) super.onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}
