package com.zairussalamdev.kamusteknikmesin.presenter

import android.content.Context
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.zairussalamdev.kamusteknikmesin.R
import com.zairussalamdev.kamusteknikmesin.db.db
import com.zairussalamdev.kamusteknikmesin.model.Kategori
import com.zairussalamdev.kamusteknikmesin.model.Materi
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class SplashPresenter(
    private val splashView: SplashView,
    private val context: Context
) {
    fun queryData() {
        splashView.showLoader()
        doAsync {
            val pref = context.getSharedPreferences(
                context.getString(R.string.dataKey),
                Context.MODE_PRIVATE
            )
            val key = pref.getString(context.getString(R.string.dataKey), "null")
            val database = FirebaseDatabase.getInstance()
            var ref = database.getReference("/dataKey")
            ref.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {}

                override fun onDataChange(p0: DataSnapshot) {
                    val dbKey = p0.value.toString()
                    if (!(key.equals(dbKey))) {
                        context.db.use {
                            delete(Materi.TABLE_MATERI)
                            delete(Kategori.TABLE_KATEGORI)
                        }
                        ref = database.getReference("/kategori")
                        ref.addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onCancelled(p0: DatabaseError) {}

                            override fun onDataChange(p0: DataSnapshot) {
                                for (h in p0.children) {
                                    val item = h.getValue(Kategori::class.java)
                                    item?.let {
                                        context.db.use {
                                            insert(
                                                Kategori.TABLE_KATEGORI,
                                                Kategori.ID_KATEGORI to item.id_kategori,
                                                Kategori.NAME to item.name
                                            )
                                        }
                                    }
                                }
                            }
                        })
                        ref = database.getReference("/materi")
                        ref.addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onCancelled(p0: DatabaseError) {
                            }

                            override fun onDataChange(p0: DataSnapshot) {
                                for (h in p0.children) {
                                    val item = h.getValue(Materi::class.java)
                                    item?.let {
                                        context.db.use {
                                            insert(
                                                Materi.TABLE_MATERI,
                                                Materi.ID_MATERI to item.id_materi,
                                                Materi.ID_KATEGORI to item.id_kategori,
                                                Materi.TITLE to item.title,
                                                Materi.CONTENT to item.content
                                            )
                                        }
                                    }
                                }
                            }
                        })

                        with(pref.edit()) {
                            putString(context.getString(R.string.dataKey), dbKey)
                            commit()
                        }
                    }
                }
            })
            Thread.sleep(1000)
            uiThread { splashView.hideLoader() }
        }
    }
}

interface SplashView {
    fun showLoader()
    fun hideLoader()
}