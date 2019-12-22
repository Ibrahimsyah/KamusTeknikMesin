package com.zairussalamdev.kamusteknikmesin.view

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.zairussalamdev.kamusteknikmesin.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        menu1.onClick {
            startActivity<KategoriActivity>()
        }
        menu2.onClick {
            toast("Menu2 Di Klik")
        }
        search.onClick {
            startActivity<SearchActivity>()
        }
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setMessage("Anda yakin ingin keluar?")
        builder.setPositiveButton(
            "Keluar"
        ) { p0, p1 -> finish() }
        builder.setNegativeButton(
            "Tidak"
        ) { p0, p1 -> p0?.cancel() }
        val alert = builder.create()
        alert.show()
    }
}
