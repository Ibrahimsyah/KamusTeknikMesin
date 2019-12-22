package com.zairussalamdev.kamusteknikmesin.presenter

import android.content.Context
import com.zairussalamdev.kamusteknikmesin.db.db
import com.zairussalamdev.kamusteknikmesin.model.Kategori
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class KategoriPresenter(
    val context: Context,
    val view: KategoriView
) {
    fun getKategori() {
        view.showLoading()
        doAsync {
            var items: List<Kategori> = mutableListOf()
            context.db.use {
                val res = select(Kategori.TABLE_KATEGORI)
                items = res.parseList(classParser())
            }
            uiThread {
                view.showData(items)
            }
        }
    }
}

interface KategoriView {
    fun showLoading()
    fun showData(kategoriList: List<Kategori>)
}