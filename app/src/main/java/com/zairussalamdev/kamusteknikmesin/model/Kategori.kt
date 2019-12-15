package com.zairussalamdev.kamusteknikmesin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Kategori (
    val id_kategori : Int?,
    val name : String?
) : Parcelable{
    constructor() : this(0, "")

    companion object{
        val TABLE_KATEGORI = "TABLE_KATEGORI"
        val ID_KATEGORI = "ID_KATEGORI"
        val NAME = "NAME"
    }
}