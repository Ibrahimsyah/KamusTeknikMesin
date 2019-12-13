package com.zairussalamdev.kamusteknikmesin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Materi(
    val content : String,
    val id_kategori: String,
    val title : String
) : Parcelable{
    constructor() : this("", "", "")
}