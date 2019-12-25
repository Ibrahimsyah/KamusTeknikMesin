package com.zairussalamdev.kamusteknikmesin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Kategori(
    val id_kategori: Int?,
    val name: String?
) : Parcelable {
    constructor() : this(0, "")

    companion object {
        const val TABLE_KATEGORI = "TABLE_KATEGORI"
        const val ID_KATEGORI = "ID_KATEGORI"
        const val NAME = "NAME"
    }
}