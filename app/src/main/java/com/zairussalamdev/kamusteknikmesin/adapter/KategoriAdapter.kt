package com.zairussalamdev.kamusteknikmesin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.zairussalamdev.kamusteknikmesin.R
import com.zairussalamdev.kamusteknikmesin.model.Kategori
import kotlinx.android.synthetic.main.item_rv.view.*

class KategoriAdapter(
    private val context: Context,
    private val items: List<Kategori>,
    private val listener: (Kategori) -> Unit
) : RecyclerView.Adapter<KategoriAdapter.KategoriViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KategoriViewHolder =
        KategoriViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rv, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: KategoriViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    class KategoriViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(item: Kategori, listener: (Kategori) -> Unit) {
            itemView.tvContent.text = item.name
            val letter = item.name?.substring(0, 1)
            val color = ColorGenerator.MATERIAL.randomColor
            val drawable = TextDrawable.builder()
                .buildRoundRect(letter, color, 100)
            itemView.Letter.setImageDrawable(drawable)
            itemView.setOnClickListener { listener(item) }
        }
    }
}