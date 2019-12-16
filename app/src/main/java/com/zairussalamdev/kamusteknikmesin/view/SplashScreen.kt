package com.zairussalamdev.kamusteknikmesin.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zairussalamdev.kamusteknikmesin.R
import com.zairussalamdev.kamusteknikmesin.presenter.SplashPresenter
import com.zairussalamdev.kamusteknikmesin.presenter.SplashView
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
