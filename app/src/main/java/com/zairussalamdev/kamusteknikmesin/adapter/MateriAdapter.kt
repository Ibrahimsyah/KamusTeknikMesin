package com.zairussalamdev.kamusteknikmesin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.zairussalamdev.kamusteknikmesin.R
import com.zairussalamdev.kamusteknikmesin.model.Materi
import kotlinx.android.synthetic.main.item_rv.view.*

class MateriAdapter(
    val context: Context,
    val items: List<Materi>,
    val listener: (Materi) -> Unit
) : RecyclerView.Adapter<MateriAdapter.MateriViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MateriViewHolder =
        MateriViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rv, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MateriViewHolder, position: Int) {
        holder.bindItem(items.get(position), listener)
    }

    class MateriViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(item: Materi, listener: (Materi) -> Unit) {
            itemView.tvContent.text = item.title
            val letter = item.title?.substring(0, 1)
            val color = ColorGenerator.MATERIAL.randomColor
            val drawable = TextDrawable.builder()
                .buildRoundRect(letter, color, 100)
            itemView.Letter.setImageDrawable(drawable)
            itemView.setOnClickListener { listener(item) }
        }
    }
}