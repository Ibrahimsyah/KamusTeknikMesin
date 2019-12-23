package com.zairussalamdev.kamusteknikmesin.view

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.zairussalamdev.kamusteknikmesin.R
import com.zairussalamdev.kamusteknikmesin.adapter.MateriAdapter
import com.zairussalamdev.kamusteknikmesin.model.Materi
import com.zairussalamdev.kamusteknikmesin.presenter.FavPresenter
import com.zairussalamdev.kamusteknikmesin.presenter.FavView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), FavView {

    val presenter = FavPresenter(this, this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        favRecycler.layoutManager = LinearLayoutManager(this)
        favRecycler.setHasFixedSize(true)
        presenter.getFav()

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

    override fun showLoading() {
        favRecycler.adapter = MateriAdapter(this, mutableListOf()) {}
        pbFav.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        presenter.getFav()
    }

    override fun showData(favList: List<Materi>) {
        pbFav.visibility = View.GONE
        if (favList.isNotEmpty()) {
            favNoResult.visibility = View.GONE
            favRecycler.adapter = MateriAdapter(this, favList) {
                startActivity<DetailActivity>("id_materi" to it.id_materi)
            }
        } else {
            favNoResult.visibility = View.VISIBLE
        }
    }
}
