package com.zairussalamdev.kamusteknikmesin.presenter

import android.content.Context
import com.zairussalamdev.kamusteknikmesin.db.db
import com.zairussalamdev.kamusteknikmesin.model.Materi
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class FavPresenter(
    val context: Context,
    val view: FavView
) {
    fun getFav() {
        view.showLoading()
        var items: List<Materi> = mutableListOf()
        doAsync {
            context.db.use {
                val query =
                    "select f.id_materi, id_kategori, title, content, img from ${Materi.TABLE_FAV} f inner join ${Materi.TABLE_MATERI} m on f.id_materi = m.id_materi"
                val res = rawQuery(
                    query,
                    null
                )
                items = res.parseList(classParser())
            }
            uiThread {
                view.showData(items)
            }
        }
    }
}

interface FavView {
    fun showLoading()
    fun showData(favList: List<Materi>)
}