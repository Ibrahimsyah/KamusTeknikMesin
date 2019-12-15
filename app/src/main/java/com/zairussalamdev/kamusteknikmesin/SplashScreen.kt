package com.zairussalamdev.kamusteknikmesin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.zairussalamdev.kamusteknikmesin.db.db
import com.zairussalamdev.kamusteknikmesin.model.Kategori
import com.zairussalamdev.kamusteknikmesin.model.Materi
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        doAsync {
            queryToDb()
        }

        startActivity<MainActivity>()
    }

    private fun queryToDb() {
        val database = FirebaseDatabase.getInstance()
        var ref = database.getReference("/kategori")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}

            override fun onDataChange(p0: DataSnapshot) {
                for (h in p0.children) {
                    val item = h.getValue(Kategori::class.java)
                    item?.let {
                        db.use {
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
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                for (h in p0.children) {
                    val item = h.getValue(Materi::class.java)
                    item?.let {
                        db.use {
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
    }
}
