package com.zairussalamdev.kamusteknikmesin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }
}
