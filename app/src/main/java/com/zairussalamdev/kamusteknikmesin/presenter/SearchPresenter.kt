package com.zairussalamdev.kamusteknikmesin.presenter

import android.content.Context
import com.zairussalamdev.kamusteknikmesin.db.db
import com.zairussalamdev.kamusteknikmesin.model.Materi
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class SearchPresenter(
    val context: Context,
    val searchViews: SearchViews
) {
    fun findMateri(query: String) {
        searchViews.showLoader()
        var lists: List<Materi> = mutableListOf()
        doAsync {
            context.db.use {
                val res = select(Materi.TABLE_MATERI).whereArgs("${Materi.TITLE} like '%$query%'")
                lists = res.parseList(classParser())
            }
            uiThread {
                searchViews.showData(lists)
            }
        }
    }
}

interface SearchViews {
    fun showLoader()
    fun showData(materis: List<Materi>)
}