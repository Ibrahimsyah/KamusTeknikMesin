package com.zairussalamdev.kamusteknikmesin

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.zairussalamdev.kamusteknikmesin.adapter.MateriAdapter
import com.zairussalamdev.kamusteknikmesin.db.db
import com.zairussalamdev.kamusteknikmesin.model.Materi
import kotlinx.android.synthetic.main.activity_materi.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity

class MateriActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materi)

        val id_kategori = intent.getIntExtra("id_kategori", 0)
        var items : List<Materi> = mutableListOf()
        db.use{
            val res = select(Materi.TABLE_MATERI).whereArgs("${Materi.ID_KATEGORI} = $id_kategori")
            items = res.parseList(classParser())
        }
        rvMateri.layoutManager = LinearLayoutManager(this)
        rvMateri.setHasFixedSize(true)
        rvMateri.adapter = MateriAdapter(this, items){
            startActivity<DetailActivity>("id_materi" to it.id_materi)
        }
    }
}
