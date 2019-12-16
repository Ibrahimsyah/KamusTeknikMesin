package com.zairussalamdev.kamusteknikmesin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.zairussalamdev.kamusteknikmesin.db.db
import com.zairussalamdev.kamusteknikmesin.model.Materi
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id_materi = intent.getIntExtra("id_materi", 0)
        var materi = Materi(0,0,"Default Materi","")
        db.use {
            val res =select(Materi.TABLE_MATERI).whereArgs("id_materi = $id_materi")
            materi = res.parseSingle(classParser())
        }
        contentToolbar.title = materi.title
        setSupportActionBar(contentToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home)super.onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}
