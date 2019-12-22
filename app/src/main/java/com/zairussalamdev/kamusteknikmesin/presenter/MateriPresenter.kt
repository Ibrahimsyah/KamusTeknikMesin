package com.zairussalamdev.kamusteknikmesin.presenter

import android.content.Context
import com.zairussalamdev.kamusteknikmesin.db.db
import com.zairussalamdev.kamusteknikmesin.model.Materi
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MateriPresenter(
    val context: Context,
    val view: MateriView
) {
    fun getMateri(id: Int) {
        view.showLoading()
        doAsync {
            var items: List<Materi> = mutableListOf()
            context.db.use {
                val res =
                    select(Materi.TABLE_MATERI).whereArgs("${Materi.ID_KATEGORI} = $id")
                items = res.parseList(classParser())
            }
            uiThread {
                view.showData(items)
            }
        }
    }
}

interface MateriView {
    fun showLoading()
    fun showData(materiList: List<Materi>)
}