package com.zairussalamdev.kamusteknikmesin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Materi(
    val id_materi: Int?,
    val id_kategori: Int?,
    val title: String?,
    val content: String?
) : Parcelable {
    constructor() : this(0, 0, "", "")

    companion object {
        val TABLE_MATERI = "TABLE_MATERI"
        val TABLE_FAV = "TABLE_FAV"
        val ID_MATERI = "ID_MATERI"
        val CONTENT = "CONTENT"
        val ID_KATEGORI = "ID_KATEGORI"
        val TITLE = "TITLE"
    }
}

data class FavMateri(
    val id_materi: Int?
)