package com.zairussalamdev.kamusteknikmesin.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.storage.FirebaseStorage
import com.zairussalamdev.kamusteknikmesin.GlideApp
import com.zairussalamdev.kamusteknikmesin.R
import com.zairussalamdev.kamusteknikmesin.db.db
import com.zairussalamdev.kamusteknikmesin.model.FavMateri
import com.zairussalamdev.kamusteknikmesin.model.Materi
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class DetailActivity : AppCompatActivity() {

    private var isFav = true
    lateinit var materi: Materi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val idMateri = intent.getIntExtra("id_materi", 0)
        materi = Materi(0, 0, "Default Materi", "", "")
        db.use {
            var res = select(Materi.TABLE_MATERI).whereArgs("id_materi = $idMateri")
            materi = res.parseSingle(classParser())
            res = select(Materi.TABLE_FAV).whereArgs("id_materi = $idMateri")
            isFav = res.parseList(classParser<FavMateri>()).isNotEmpty()
        }
        contentTitle.isSelected = true
        contentTitle.text = materi.title

        setSupportActionBar(contentToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        content.text = materi.content
        val imgPath = materi.img
        if (imgPath != null) {
            if (imgPath.isNotEmpty()) {
                val storageReference = FirebaseStorage.getInstance().reference.child(imgPath)
                GlideApp.with(this)
                    .load(storageReference)
                    .into(contentImage)
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) super.onBackPressed()
        if (item.itemId == R.id.favourite) {
            if (!isFav) {
                db.use {
                    insert(
                        Materi.TABLE_FAV,
                        Materi.ID_MATERI to materi.id_materi
                    )
                }
                toast("${materi.title} ditambahkan ke favorit")
                item.setIcon(R.drawable.ic_favorite_active)
            } else {
                db.use {
                    delete(Materi.TABLE_FAV, "${Materi.ID_MATERI} = '${materi.id_materi}'")
                }
                toast("${materi.title} dihapus dari favorit")
                item.setIcon(R.drawable.ic_favorite)
            }
            isFav = !isFav
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_fav, menu)
        if (isFav) menu?.getItem(0)?.setIcon(R.drawable.ic_favorite_active)
        return super.onCreateOptionsMenu(menu)
    }

}
