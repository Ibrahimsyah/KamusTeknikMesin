package com.zairussalamdev.kamusteknikmesin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Materi(
    val content : String,
    val id_kategori: Int,
    val title : String
) : Parcelable