package com.zairussalamdev.kamusteknikmesin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.zairussalamdev.kamusteknikmesin.model.Kategori
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        menu1.onClick {
            toast("Menu1 Di Klik")
        }
        menu2.onClick {
            toast("Menu2 Di Klik")
        }

        val ref = FirebaseDatabase.getInstance().getReference("kategori")
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                for(h in p0.children){
                    TODO("Add Action after Querying DB")
                }
            }
            override fun onCancelled(p0: DatabaseError) {
            }



        })
    }
}
