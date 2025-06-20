package com.example.modul3xml.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RobloxGame(
    val id: Int,
    val title: String,
    val genre: String,
    val players: String,
    val rating: String,
    val imageUrl: Int,
    val gameUrl: String,
    val releaseDate: String,
    val description: String,
    val developer: String
) : Parcelable 