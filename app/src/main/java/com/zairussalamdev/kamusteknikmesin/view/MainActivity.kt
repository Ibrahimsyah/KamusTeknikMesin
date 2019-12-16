package com.zairussalamdev.kamusteknikmesin.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.zairussalamdev.kamusteknikmesin.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }

        menu1.onClick {
            startActivity<KategoriActivity>()
        }
        menu2.onClick {
            toast("Menu2 Di Klik")
        }
        search.onClick {
            startActivity<SearchActivity>()
        }
        val ref = FirebaseDatabase.getInstance().getReference("kategori")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                for (h in p0.children) {
//                    TODO("Add Action after Querying DB")
                }
            }

            override fun onCancelled(p0: DatabaseError) {
            }


        })
    }
}
