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
import com.zairussalamdev.kamusteknikmesin.presenter.SplashPresenter
import com.zairussalamdev.kamusteknikmesin.presenter.SplashView
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity

class SplashScreen : AppCompatActivity(), SplashView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        val presenter = SplashPresenter(this, applicationContext)
        presenter.queryData()
    }

    override fun showLoader() {}

    override fun hideLoader() {
        startActivity<MainActivity>()
        finish()
    }

}
