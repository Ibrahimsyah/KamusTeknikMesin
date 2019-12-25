package com.zairussalamdev.kamusteknikmesin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Materi(
    val id_materi: Int?,
    val id_kategori: Int?,
    val title: String?,
    val content: String?,
    val img: String?
) : Parcelable {
    constructor() : this(0, 0, "", "", "")

    companion object {
        const val TABLE_MATERI = "TABLE_MATERI"
        const val TABLE_FAV = "TABLE_FAV"
        const val ID_MATERI = "ID_MATERI"
        const val ID_KATEGORI = "ID_KATEGORI"
        const val TITLE = "TITLE"
        const val CONTENT = "CONTENT"
        const val IMG = "IMG"
    }
}

data class FavMateri(
    val id_materi: Int?
)